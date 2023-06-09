package com.example.simple_chatting.dto.channel;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetChannelInvitationCodeResponse {
    @NotEmpty
    private String invitationCode;

    public static GetChannelInvitationCodeResponse of(String invitationCode) {
        return new GetChannelInvitationCodeResponse(invitationCode);
    }
}
