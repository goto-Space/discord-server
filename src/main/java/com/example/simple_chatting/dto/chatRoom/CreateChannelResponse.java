package com.example.simple_chatting.dto.chatRoom;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateChannelResponse {
    private Long channelId;

    @Builder
    public CreateChannelResponse(Long channelId) {
        this.channelId = channelId;
    }
}
