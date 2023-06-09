package com.example.simple_chatting.dto.user;

import com.example.simple_chatting.domain.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpUserResponse {
    @NotNull
    Long userId;

    public static SignUpUserResponse of(User user) {
        return new SignUpUserResponse(user.getId());
    }
}
