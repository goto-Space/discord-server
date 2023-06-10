package com.example.simple_chatting.dto.user;

import com.example.simple_chatting.domain.user.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpUserRequest {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    public User toEntity() {
        return User.builder()
            .name(userName)
            .loginId(loginId)
            .password(password)
            .build();
    }
}
