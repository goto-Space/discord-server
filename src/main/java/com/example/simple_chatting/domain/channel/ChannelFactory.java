package com.example.simple_chatting.domain.channel;

import com.example.simple_chatting.dto.chatRoom.CreateChannelRequest;
import org.springframework.stereotype.Component;

@Component
public class ChannelFactory {
    public Channel makeChannel(CreateChannelRequest request) throws IllegalArgumentException {
        switch (request.getType()) {
            case TEXT -> {
                return new TextChannel().builder()
                    .type(request.getType())
                    .name(request.getName())
                    .build();
            }
            case VOICE_ONLY -> {
                return new VoiceOnlyChannel().builder()
                    .type(request.getType())
                    .name(request.getName())
                    .build();
            }
            case VIDEO -> {
                return new VideoChannel().builder()
                    .type(request.getType())
                    .name(request.getName())
                    .build();
            }
            default -> throw new IllegalArgumentException("잘못된 방 종류입니다.");
        }
    }
}
