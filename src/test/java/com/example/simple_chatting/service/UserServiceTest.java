package com.example.simple_chatting.service;

import com.example.simple_chatting.dto.user.RegisterUserRequest;
import com.example.simple_chatting.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {

    @Autowired UserService userService;

    @Autowired UserRepository userRepository;


    @Test
    @DisplayName("회원 로그인 아이디는 중복될 수 없다.")
    void join() {
        String name1 = "shw";
        String loginId1 = "network-protocol";
        String password1 = "201800";
        RegisterUserRequest request1 = new RegisterUserRequest(name1, loginId1, password1);

        String name2 = "bj";
        String loginId2 = "network-protocol";
        String password2 = "201800";
        RegisterUserRequest request2 = new RegisterUserRequest(name2, loginId2, password2);

        userService.join(request1);

        assertThrows(IllegalStateException.class, () -> {
            userService.join(request2);
        });
    }
}