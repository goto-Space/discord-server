package com.example.simple_chatting.dto.user;

import com.example.simple_chatting.domain.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterUserRequest {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    @Builder
    public RegisterUserRequest(String userName, String loginId, String password) {
        this.userName = userName;
        this.loginId = loginId;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
            .name(userName)
            .loginId(loginId)
            .password(password)
            .build();
    }
}
