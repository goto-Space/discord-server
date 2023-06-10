package com.example.simple_chatting.dto.user;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllUserRequest {
    @NotNull
    private List<Long> userIds;
}
