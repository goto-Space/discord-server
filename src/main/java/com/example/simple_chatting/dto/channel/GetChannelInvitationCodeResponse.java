package com.example.simple_chatting.dto.channel;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetChannelInvitationCodeResponse {
    @NotEmpty
    private String invitationCode;

    @Builder
    public GetChannelInvitationCodeResponse(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
