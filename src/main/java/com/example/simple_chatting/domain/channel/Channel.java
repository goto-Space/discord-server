package com.example.simple_chatting.domain.channel;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.user.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Channel {
    private Long id;
    private ChannelType type;
    private String name;
    private List<User> users;

    public Channel(ChannelType type, String name) {
        this.type = type;
        this.name = name;
        this.users = new LinkedList<>();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
