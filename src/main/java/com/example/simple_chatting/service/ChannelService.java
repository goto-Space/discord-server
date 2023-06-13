package com.example.simple_chatting.service;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.channel.Channel;
import com.example.simple_chatting.domain.channel.ChannelFactory;
import com.example.simple_chatting.domain.channel.VideoChannel;
import com.example.simple_chatting.domain.channel.VoiceOnlyChannel;
import com.example.simple_chatting.domain.user.User;
import com.example.simple_chatting.dto.channel.CreateChannelRequest;
import com.example.simple_chatting.dto.channel.CreateChannelResponse;
import com.example.simple_chatting.dto.channel.FindAllChannelResponse;
import com.example.simple_chatting.dto.channel.FindChannelResponse;
import com.example.simple_chatting.dto.channel.GetChannelInvitationCodeResponse;
import com.example.simple_chatting.dto.channel.JoinChannelByInvitationCodeResponse;
import com.example.simple_chatting.dto.channel.JoinChannelRequest;
import com.example.simple_chatting.repository.ChannelRepository;
import com.example.simple_chatting.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelFactory channelFactory;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public CreateChannelResponse create(CreateChannelRequest request, Long userId) {
        validateDuplicateChannelTypeAndName(request.getChannelType(), request.getChannelName());
        User user = userRepository.findById(userId);
        Channel channel = channelFactory.makeChannel(request, userId);
        channel.join(user);
        Channel savedChannel = channelRepository.save(channel);
        return CreateChannelResponse.of(savedChannel);
    }

    public void deleteById(Long channelId, Long userId) {
        validateChannelExistence(channelId);
        Channel channel = channelRepository.findById(channelId);
        validateChannelHost(channel.getHostUserId(), userId);
        channelRepository.deleteById(channelId);
    }

    public void join(JoinChannelRequest request, Long channelId, Long userId) {
        validateChannelExistence(channelId);
        Channel channel = channelRepository.findById(channelId);
        User user = userRepository.findById(userId);
        validateInvitationCode(channel, request.getInvitationCode());
        channel.join(user);
        channelRepository.save(channel);
    }

    public JoinChannelByInvitationCodeResponse joinByInvitationCode(String invitationCode, Long userId) {
        Channel channel = channelRepository.findByInvitationCode(invitationCode)
            .orElseThrow(() -> new IllegalArgumentException("초대 코드가 유효하지않습니다."));
        User user = userRepository.findById(userId);
        channel.join(user);
        channelRepository.save(channel);
        return JoinChannelByInvitationCodeResponse.of(channel);
    }

    public GetChannelInvitationCodeResponse getInvitationCode(Long channelId, Long userId) {
        validateChannelExistence(channelId);
        Channel channel = channelRepository.findById(channelId);
        User user = userRepository.findById(userId);
        validateChannelUser(channel, user);
        return GetChannelInvitationCodeResponse.of(channel.getInvitationCode());
    }

    public void releaseUser(Long channelId, Long userId) {
        validateChannelExistence(channelId);
        Channel channel = channelRepository.findById(channelId);
        User user = userRepository.findById(userId);
        validateChannelUser(channel, user);
        channel.release(user);

        if (channel.isLeftUser()) {
            channelRepository.save(channel);
        } else {
            channelRepository.deleteById(channelId);
        }
    }

    public boolean existsById(Long channelId) {
        return channelRepository.existsById(channelId);
    }

    public FindChannelResponse findById(Long channelId, Long userId) {
        validateChannelExistence(channelId);
        Channel channel = channelRepository.findById(channelId);
        User user = userRepository.findById(userId);
        validateChannelUser(channel, user);

        return FindChannelResponse.of(channel);
    }

    public List<WebSocketSession> getChannelClientSessions(Long channelId) {
        Channel channel = channelRepository.findById(channelId);

        if (channel.getType().equals(ChannelType.VOICE_ONLY)) {
            VoiceOnlyChannel voiceOnlyChannel = (VoiceOnlyChannel) channel;
            return voiceOnlyChannel.getClientSessions();
        }

        if (channel.getType().equals(ChannelType.VIDEO)) {
            VideoChannel videoChannel = (VideoChannel) channel;
            return videoChannel.getClientSessions();
        }

        return null;
    }

    public FindAllChannelResponse findAll(Long userId) {
        User user = userRepository.findById(userId);
        List<Channel> findChannels = channelRepository.findAllByUser(user);

        return findChannels.stream()
            .map(FindChannelResponse::of)
            .collect(collectingAndThen(toList(), findChannelResponses -> FindAllChannelResponse.of(findChannelResponses)));
    }

    private void validateDuplicateChannelTypeAndName(ChannelType type, String name) {
        Optional<Channel> findChannel = channelRepository.findByTypeAndName(type, name);
        if (!findChannel.isEmpty()) {
            throw new IllegalArgumentException("같은 채널 내에 동일한 이름을 가지는 다른 채널이 이미 존재합니다.");
        }
    }

    private void validateChannelExistence(Long channelId) {
        if (!channelRepository.existsById(channelId)) {
            throw new IllegalArgumentException("해당 채널은 존재하지 않습니다.");
        }
    }

    private void validateChannelHost(Long hostUserId, Long userId) {
        if (hostUserId != userId) {
            throw new IllegalArgumentException("방장이 아닙니다.");
        }
    }

    private void validateInvitationCode(Channel channel, String invitationCode) {
        if (!channel.match(invitationCode)) {
            throw new IllegalArgumentException("채널 초대 코드가 일치하지 않습니다.");
        }
    }

    private void validateChannelUser(Channel channel, User user) {
        if (!channel.contains(user)) {
            throw new IllegalStateException("요청한 사용자가 채널에 속해있지 않습니다.");
        }
    }
}
