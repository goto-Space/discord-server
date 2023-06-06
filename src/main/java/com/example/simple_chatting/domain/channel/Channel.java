package com.example.simple_chatting.domain.channel;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.user.User;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
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
    private String invitationCode;

    public Channel(ChannelType type, String name, String hostUserLoginId) {
        this.type = type;
        this.name = name;
        this.users = new LinkedList<>();
        this.hostUserLoginId = hostUserLoginId;
        this.invitationCode = UUID.randomUUID().toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void join(User user) {
        if (isEqualOrOverThanMaxUser()) {
            throw new IllegalStateException("한 채팅방에는 동시에 최대 6명까지 참여할 수 있습니다.");
        }
        users.add(user);
    }

    public void changeHost(User user) {
        this.hostUserLoginId = user.getLoginId();
    }

    private boolean isEqualOrOverThanMaxUser() {
        return users.size() >= MAX_USER;
    }
}
