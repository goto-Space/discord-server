package com.example.simple_chatting.dto.user;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegisterUserResponse {
    long userId;

    @Builder
    public RegisterUserResponse(long userId) {
        this.userId = userId;
    }
}
