package com.example.simple_chatting.service;

import com.example.simple_chatting.domain.channel.Channel;
import com.example.simple_chatting.domain.channel.ChannelFactory;
import com.example.simple_chatting.dto.chatRoom.CreateChannelRequest;
import com.example.simple_chatting.repository.ChannelRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelFactory channelFactory;
    private final ChannelRepository channelRepository;

    public Long createChannel(CreateChannelRequest request) {
        validateDuplicateChannel(request);

        Channel channel = channelFactory.makeChannel(request);
        Channel createdChannel = channelRepository.save(channel);
        return createdChannel.getId();
    }

    private void validateDuplicateChannel(CreateChannelRequest request) {
        Optional<Channel> findChatRoom = channelRepository.findByTypeAndName(request.getType(), request.getName());
        if (!findChatRoom.isEmpty()) {
            throw new IllegalArgumentException("같은 채널 내에 동일한 이름을 가지는 다른 채널이 이미 존재합니다.");
        }
    }
}
