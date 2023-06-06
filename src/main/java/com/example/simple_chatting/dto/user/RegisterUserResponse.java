package com.example.simple_chatting.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterUserResponse {
    Long userId;

    @Builder
    public RegisterUserResponse(Long userId) {
        this.userId = userId;
    }
}
