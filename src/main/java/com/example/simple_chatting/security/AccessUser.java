package com.example.simple_chatting.security;

import com.example.simple_chatting.dto.user.LoginUserRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessUser {
    private final String loginId;
    private final String userName;

    public static AccessUser of(final String loginId, final String userName) {
        return new AccessUser(loginId, userName);
    }
}
