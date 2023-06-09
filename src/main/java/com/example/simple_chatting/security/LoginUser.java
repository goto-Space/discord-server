package com.example.simple_chatting.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginUser {
    private final Long id;
    private final String name;

    public static LoginUser of(final Long id, final String name) {
        return new LoginUser(id, name);
    }
}
