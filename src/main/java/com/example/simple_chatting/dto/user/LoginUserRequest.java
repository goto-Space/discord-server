package com.example.simple_chatting.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserRequest {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
