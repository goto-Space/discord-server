package com.example.simple_chatting.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginUserRequest {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    @Builder
    public LoginUserRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
