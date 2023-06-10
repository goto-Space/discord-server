package com.example.simple_chatting.repository;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.channel.Channel;
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
public class ChannelRepositoryImpl implements ChannelRepository {
    private static final Map<Long, Channel> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequenceNumber = new AtomicLong();

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

    @Override
    public Channel save(Channel channel) {
        if (channel.getId() == null) {
            channel.setId(sequenceNumber.incrementAndGet());
        }
        store.put(channel.getId(), channel);
        return channel;
    }

    @Override
    public Channel findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Channel> findByTypeAndName(ChannelType type, String roomName) {
        return findAll().stream()
            .filter(room -> room.getType().equals(type) && room.getName().equals(roomName))
            .findFirst();
    }

    @Override
    public Optional<Channel> findByName(String roomName) {
        return findAll().stream()
            .filter(channel -> channel.getName().equals(roomName))
            .findFirst();
    }

    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public List<Channel> findAllByUser(User user) {
        return findAll().stream()
            .filter(channel -> channel.contains(user))
            .collect(Collectors.toList());
    }
}
