package com.example.simple_chatting.dto.channel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetChannelInvitationCodeResponse {
    private String invitationCode;

    @Builder
    public GetChannelInvitationCodeResponse(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
