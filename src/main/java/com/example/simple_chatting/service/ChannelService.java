package com.example.simple_chatting.service;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.channel.Channel;
import com.example.simple_chatting.domain.channel.ChannelFactory;
import com.example.simple_chatting.domain.user.User;
import com.example.simple_chatting.dto.channel.CreateChannelRequest;
import com.example.simple_chatting.dto.channel.JoinChannelRequest;
import com.example.simple_chatting.repository.ChannelRepository;
import com.example.simple_chatting.repository.UserRepository;
import com.example.simple_chatting.security.AccessUser;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelFactory channelFactory;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public Long createChannel(CreateChannelRequest request, AccessUser accessUser) {
        validateDuplicateChannel(request);
        User user = validateAndFindUser(accessUser);

        Channel createdChannel = channelFactory.makeChannel(request, accessUser);
        createdChannel.join(user);

        Channel savedChannel = channelRepository.save(createdChannel);
        return savedChannel.getId();
    }

    public void deleteById(Long id, AccessUser accessUser) {
        validateToDelete(id, accessUser);
        channelRepository.deleteById(id);
    }

    public void join(JoinChannelRequest request, AccessUser accessUser, Long channelId) {
        User user = validateAndFindUser(accessUser);
        Channel channel = checkExistAndFindChannel(channelId);
        validateInvitationCode(channel, request);
        channel.join(user);

        channelRepository.save(channel);
    }

    public String getInvitationCode(AccessUser accessUser, Long channelId) {
        User requestUser = validateAndFindUser(accessUser);
        Channel channel = checkExistAndFindChannel(channelId);
        validateChannelUser(channel, requestUser);
        return channel.getInvitationCode();
    }

    public void leave(AccessUser accessUser, Long channelId) {
        User user = validateAndFindUser(accessUser);
        Channel channel = checkExistAndFindChannel(channelId);
        validateChannelUser(channel, user);
        channel.leaveUser(user);

        if (channel.isLeftUser()) {
            channelRepository.save(channel);
        }
        else {
            channelRepository.deleteById(channelId);
        }
    }

    public ChannelType getChannelType(Long channelId) {
        Channel channel = checkExistAndFindChannel(channelId);
        return channel.getType();
    }

    private void validateDuplicateChannel(CreateChannelRequest request) {
        Optional<Channel> findChannel = channelRepository.findByTypeAndName(request.getType(), request.getName());
        if (!findChannel.isEmpty()) {
            throw new IllegalArgumentException("같은 채널 내에 동일한 이름을 가지는 다른 채널이 이미 존재합니다.");
        }
    }

    private User validateAndFindUser(AccessUser accessUser) {
        String accessUserLoginId = accessUser.getLoginId();
        User user = userRepository.findByLoginId(accessUserLoginId)
            .orElseThrow(() -> new IllegalStateException("현재 로그인 회원은 존재하지 않는 회원입니다."));
        return user;
    }

    private void validateToDelete(Long id, AccessUser accessUser) {
        Channel findChannel = checkExistAndFindChannel(id);
        checkHost(findChannel, accessUser);
    }

    private Channel checkExistAndFindChannel(Long id) {
        Channel findChannel = channelRepository.findById(id);
        if (findChannel == null) {
            throw new IllegalArgumentException("해당 채널은 존재하지 않습니다.");
        }
        return findChannel;
    }

    private void checkHost(Channel channel, AccessUser accessUser) {
        if (!channel.getHostUserLoginId().equals(accessUser.getLoginId())) {
            throw new IllegalArgumentException("방장이 아닙니다.");
        }
    }

    private void validateInvitationCode(Channel channel, JoinChannelRequest request) {
        if (!channel.matchInvitationCode(request.getInvitationCode())) {
            throw new IllegalArgumentException("채널 코드가 일치하지 않습니다.");
        }
    }

    private void validateChannelUser(Channel channel, User user) {
        if (!channel.isUserIn(user)) {
            throw new IllegalStateException("요청한 사용자가 채널에 속해있지 않습니다.");
        }
    }
}
