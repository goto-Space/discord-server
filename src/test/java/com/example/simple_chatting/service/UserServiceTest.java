package com.example.simple_chatting.service;

import com.example.simple_chatting.dto.user.LoginUserRequest;
import com.example.simple_chatting.dto.user.SignUpUserRequest;
import com.example.simple_chatting.repository.UserRepository;
import com.example.simple_chatting.security.AccessUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceTest {

    @Autowired UserService userService;

    @Autowired UserRepository userRepository;

    @AfterEach
    void after() {
        userRepository.clear();
    }

    @Test
    @DisplayName("회원 로그인 아이디는 중복될 수 없다.")
    void join() {
        //given
        String name1 = "shw";
        String loginId1 = "network-protocol";
        String password1 = "201800";
        SignUpUserRequest request1 = new SignUpUserRequest(name1, loginId1, password1);

        String name2 = "bj";
        String loginId2 = "network-protocol";
        String password2 = "201800";
        SignUpUserRequest request2 = new SignUpUserRequest(name2, loginId2, password2);

        //when
        userService.signUp(request1);

        //then
        assertThrows(IllegalStateException.class, () -> {
            userService.signUp(request2);
        });
    }

    @Test
    @DisplayName("회원 1명 정상 로그인")
    void login() {
        //given
        String name = "sonny";
        String loginId = "network-protocol";
        String password = "2018";
        SignUpUserRequest signUpUserRequest = new SignUpUserRequest(name, loginId, password);
        LoginUserRequest loginUserRequest = new LoginUserRequest(loginId, password);

        //when
        userService.signUp(signUpUserRequest);
        AccessUser accessUser = userService.login(loginUserRequest);

        //then
        assertEquals(loginId, accessUser.getLoginId());
    }

//    @Test
//    @DisplayName("회원 1명 정상 로그아웃")
//    void logout() {
//        //given
//        String name = "sonny";
//        String loginId = "network-protocol";
//        String password = "2018";
//        RegisterUserRequest registerUserRequest = new RegisterUserRequest(name, loginId, password);
//        LoginUserRequest loginUserRequest = new LoginUserRequest(loginId, password);
//
//        //when
//        userService.join(registerUserRequest);
//        AccessUser accessUser = userService.login(loginUserRequest);
//        userSessionManager.deleteUser();
//
//        //then
//        assertNull(userSessionManager.extractUser());
//    }

}