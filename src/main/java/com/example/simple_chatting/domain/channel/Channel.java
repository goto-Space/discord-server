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
    private Long hostUserId;
    private String invitationCode;

    public Channel(ChannelType type, String name, Long hostUserId) {
        this.type = type;
        this.name = name;
        this.users = new LinkedList<>();
        this.hostUserId = hostUserId;
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

    public boolean contains(User user) {
        return users.contains(user);
    }

    public boolean match(String invitationCode) {
        return this.invitationCode.equals(invitationCode);
    }

    public void release(User user) {
        users.remove(user);

        if (this.hostUserId == user.getId()) {
            changeHost();
        }
    }

    public boolean isLeftUser() {
        return getNumberOfUsersIn() > 0;
    }

    public Integer getNumberOfUsersIn() {
        return users.size();
    }

    private boolean isEqualOrOverThanMaxUser() {
        return users.size() >= MAX_USER;
    }

    private void changeHost() {
        if (!users.isEmpty()) {
            this.hostUserLoginId = users.get(0).getLoginId();
        }
    }
}
