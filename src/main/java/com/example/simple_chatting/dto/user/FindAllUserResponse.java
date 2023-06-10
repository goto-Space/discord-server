package com.example.simple_chatting.dto.user;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindAllUserResponse {
    private List<FindUserResponse> users;

    public static FindAllUserResponse of(List<FindUserResponse> findUserResponses) {
        return new FindAllUserResponse(findUserResponses);
    }
}
