package com.example.simple_chatting.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessUser {
    private final Long id;
    private final String name;

    public static AccessUser of(final Long id, final String name) {
        return new AccessUser(id, name);
    }
}
