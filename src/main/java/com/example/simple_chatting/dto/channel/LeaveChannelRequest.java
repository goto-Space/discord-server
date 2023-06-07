package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.common.ChannelType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LeaveChannelRequest {
    private ChannelType channelType;

    @Builder
    public LeaveChannelRequest(ChannelType channelType) {
        this.channelType = channelType;
    }
}
