package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.common.ChannelType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LeaveChannelRequest {
    @NotNull
    private ChannelType channelType;
}
