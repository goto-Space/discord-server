package com.example.simple_chatting.web;

import com.example.simple_chatting.dto.user.RegisterUserRequest;
import com.example.simple_chatting.dto.user.RegisterUserResponse;
import com.example.simple_chatting.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public RegisterUserResponse signUp(@Valid @RequestBody RegisterUserRequest request) {
        Long userId = userService.join(request);
        return new RegisterUserResponse().builder()
            .userId(userId)
            .build();
    }
}

