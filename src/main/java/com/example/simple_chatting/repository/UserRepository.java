package com.example.simple_chatting.repository;

import com.example.simple_chatting.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User User);

    User findById(Long id);

    Optional<User> findByLoginId(String loginId);

    List<User> findAll();
}
