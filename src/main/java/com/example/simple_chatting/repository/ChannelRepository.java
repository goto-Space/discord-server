package com.example.simple_chatting.repository;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.channel.Channel;
import java.util.List;
import java.util.Optional;

public interface ChannelRepository {

    Channel save(Channel channel);

    Channel findById(Long id);

    Optional<Channel> findByTypeAndName(ChannelType type, String roomName);

    Optional<Channel> findByName(String roomName);

    List<Channel> findAll();

    void clear();

    void deleteById(Long id);
}
