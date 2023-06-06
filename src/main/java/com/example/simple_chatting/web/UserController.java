package com.example.simple_chatting.web;

import com.example.simple_chatting.dto.user.LoginUserRequest;
import com.example.simple_chatting.dto.user.RegisterUserRequest;
import com.example.simple_chatting.dto.user.RegisterUserResponse;
import com.example.simple_chatting.security.AccessUser;
import com.example.simple_chatting.security.SessionConst;
import com.example.simple_chatting.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@Valid @RequestBody LoginUserRequest request, HttpServletRequest httpServletRequest) {
        AccessUser accessUser = userService.login(request);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SessionConst.USER_SESSION_KEY, accessUser);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

