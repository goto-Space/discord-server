package com.example.simple_chatting.service;

import com.example.simple_chatting.domain.user.User;
import com.example.simple_chatting.dto.user.LoginUserRequest;
import com.example.simple_chatting.dto.user.RegisterUserRequest;
import com.example.simple_chatting.repository.UserRepository;
import com.example.simple_chatting.security.AccessUser;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long join(RegisterUserRequest request) {
        validateDuplicateUser(request.getLoginId(), request.getPassword());
        User registeredUser = userRepository.save(request.toEntity());
        return registeredUser.getId();
    }

    public AccessUser login(LoginUserRequest request) {
        User user = userRepository.findByLoginId(request.getLoginId())
            .orElseThrow(() -> new IllegalStateException("사용자 정보가 일치하지 않습니다."));
        user.authenticate(request.getPassword());
        return AccessUser.of(request);
    }

    private void validateDuplicateUser(String loginId, String password) {
        Optional<User> findUser = userRepository.findByLoginIdAndPassword(loginId, password);
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디와 비밀번호입니다. 다른 아이디와 비밀번호를 입력해주세요.");
        }
    }
}
