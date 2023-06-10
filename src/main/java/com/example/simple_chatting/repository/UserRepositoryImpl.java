package com.example.simple_chatting.repository;

import com.example.simple_chatting.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Map<Long, User> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequenceNumber = new AtomicLong();

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(sequenceNumber.incrementAndGet());
        }
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return findAll().stream()
            .filter(user -> user.getLoginId().equals(loginId))
            .findFirst();
    }

    @Override
    public Optional<User> findByLoginIdAndPassword(String loginId, String password) {
        return findAll().stream()
            .filter(user -> user.getLoginId().equals(loginId) && user.getPassword().equals(password))
            .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<User> findAllByIds(List<Long> userIds) {
        return findAll().stream()
            .filter(user -> userIds.contains(user.getId()))
            .collect(Collectors.toList());
    }

    public void clear() {
        store.clear();
    }
}
