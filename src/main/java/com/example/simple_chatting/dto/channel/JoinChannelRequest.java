package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.common.ChannelType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinChannelRequest {
    private ChannelType channelType;
    @NotEmpty
    private String invitationCode;
}
