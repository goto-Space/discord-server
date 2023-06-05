package com.example.simple_chatting.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private Long id;

    private String name;
    private String loginId;
    private String password;

    @Builder
    public User(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void authenticate(String password) {
        if (!password.equals(this.password)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }
}
