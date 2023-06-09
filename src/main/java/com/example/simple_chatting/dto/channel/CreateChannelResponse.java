package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.domain.channel.Channel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateChannelResponse {
    @NotNull
    private Long channelId;

    public static CreateChannelResponse of(Channel channel) {
        return new CreateChannelResponse(channel.getId());
    }
}
