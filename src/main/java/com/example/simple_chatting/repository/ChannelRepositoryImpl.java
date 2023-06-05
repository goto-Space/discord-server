package com.example.simple_chatting.repository;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.channel.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelRepositoryImpl implements ChannelRepository {
    private static Map<Long, Channel> store = new ConcurrentHashMap<>();
    private static AtomicLong sequenceNumber = new AtomicLong();

    @Override
    public Channel save(Channel channel) {
        channel.setId(sequenceNumber.incrementAndGet());
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
            .filter(room -> room.getName().equals(roomName))
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
}
