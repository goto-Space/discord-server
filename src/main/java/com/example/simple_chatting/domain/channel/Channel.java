package com.example.simple_chatting.domain.channel;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.user.User;
import java.util.LinkedList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Channel {
    private final Integer MAX_USER = 6;
    private Long id;
    private ChannelType type;
    private String name;
    private List<User> users;
    private String hostUserLoginId;

    public Channel(ChannelType type, String name, String hostUserLoginId) {
        this.type = type;
        this.name = name;
        this.users = new LinkedList<>();
        this.hostUserLoginId = hostUserLoginId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
