package com.example.simple_chatting.service;

import com.example.simple_chatting.domain.user.User;
import com.example.simple_chatting.dto.user.FindAllUserRequest;
import com.example.simple_chatting.dto.user.FindAllUserResponse;
import com.example.simple_chatting.dto.user.FindUserResponse;
import com.example.simple_chatting.dto.user.LoginUserRequest;
import com.example.simple_chatting.dto.user.SignUpUserRequest;
import com.example.simple_chatting.dto.user.SignUpUserResponse;
import com.example.simple_chatting.repository.UserRepository;
import com.example.simple_chatting.security.LoginUser;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public SignUpUserResponse signUp(SignUpUserRequest request) {
        validateDuplicateLoginIdAndPasswordPair(request);
        User savedUser = userRepository.save(request.toEntity());
        return SignUpUserResponse.of(savedUser);
    }

    public LoginUser login(LoginUserRequest request) {
        User user = userRepository.findByLoginId(request.getLoginId())
            .orElseThrow(() -> new IllegalStateException("사용자 정보가 일치하지 않습니다."));
        user.authenticate(request.getPassword());
        return LoginUser.of(user.getId(), user.getName());
    }

    private void validateDuplicateLoginIdAndPasswordPair(SignUpUserRequest request) {
        Optional<User> findUser = userRepository.findByLoginIdAndPassword(request.getLoginId(), request.getPassword());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디와 비밀번호입니다. 다른 아이디와 비밀번호를 입력해주세요.");
        }
    }

    public FindUserResponse findById(Long userId) {
        User user = userRepository.findById(userId);
        return FindUserResponse.of(user);
    }

    public FindAllUserResponse findAll(FindAllUserRequest request) {
        List<User> findUsers = userRepository.findAllByIds(request.getUserIds());

        return findUsers.stream()
            .map(FindUserResponse::of)
            .collect(collectingAndThen(toList(), findUserResponses -> FindAllUserResponse.of(findUserResponses)));
    }
}
