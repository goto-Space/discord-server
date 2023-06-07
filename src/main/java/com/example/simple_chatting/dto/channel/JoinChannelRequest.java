package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.common.ChannelType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinChannelRequest {
    private ChannelType channelType;
    @NotEmpty
    private String invitationCode;

    @Builder
    public JoinChannelRequest(ChannelType channelType, String invitationCode) {
        this.channelType = channelType;
        this.invitationCode = invitationCode;
    }
}
