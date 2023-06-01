package com.example.simple_chatting.service;

import com.example.simple_chatting.domain.User;
import com.example.simple_chatting.dto.user.RegisterUserRequest;
import com.example.simple_chatting.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long join(RegisterUserRequest request) {
        validateDuplicateUser(request.getLoginId());
        User user = request.toEntity();
        return userRepository.save(user).getId();
    }

    private void validateDuplicateUser(String loginId) {
        Optional<User> findUser = userRepository.findByLoginId(loginId);
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
        }
    }
}
