package com.example.simple_chatting.controller;

import com.example.simple_chatting.dto.user.LoginUserRequest;
import com.example.simple_chatting.dto.user.SignUpUserRequest;
import com.example.simple_chatting.dto.user.SignUpUserResponse;
import com.example.simple_chatting.security.LoginUser;
import com.example.simple_chatting.security.SessionConst;
import com.example.simple_chatting.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(
        summary = "사용자 회원가입"
    )
    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponse> signUp(
        @RequestBody @Valid SignUpUserRequest request
    ) {
        SignUpUserResponse response = userService.signUp(request);
        return ResponseEntity
            .created(URI.create("/api/users/" + response.getUserId()))
            .body(response);
    }

    @Operation(
        summary = "사용자 로그인"
    )
    @PostMapping("/login")
    public ResponseEntity<Void> login(
        @RequestBody @Valid LoginUserRequest request,
        HttpSession session
    ) {
        LoginUser loginUser = userService.login(request);
        session.setAttribute(SessionConst.USER_SESSION_KEY, loginUser);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "사용자 로그아웃"
    )
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
        HttpServletRequest httpServletRequest
    ) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.noContent().build();
    }
}

