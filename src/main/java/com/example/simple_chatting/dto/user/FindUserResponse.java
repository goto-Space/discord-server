package com.example.simple_chatting.dto.user;

import com.example.simple_chatting.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindUserResponse {
    private String name;

    public static FindUserResponse of(User user) {
        return new FindUserResponse(user.getName());
    }
}