package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.common.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LeaveChannelRequest {
    private ChannelType channelType;
}
