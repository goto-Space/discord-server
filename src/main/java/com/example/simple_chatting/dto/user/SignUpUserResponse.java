package com.example.simple_chatting.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpUserResponse {
    Long userId;

    @Builder
    public SignUpUserResponse(Long userId) {
        this.userId = userId;
    }
}
