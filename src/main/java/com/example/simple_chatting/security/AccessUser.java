package com.example.simple_chatting.security;

import com.example.simple_chatting.dto.user.LoginUserRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessUser {
    private final String loginId;

    public static AccessUser of(LoginUserRequest loginUserRequest) {
        return new AccessUser(loginUserRequest.getLoginId());
    }
}
