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

    public boolean isUserIn(User user){
        return users.contains(user);
    }

    public boolean matchInvitationCode(String invitationCode) {
        return this.invitationCode.equals(invitationCode);
    }

    public void leaveUser(User user) {
        users.remove(user);

        if (this.hostUserLoginId.equals(user.getLoginId())) {
            changeHost();
        }
    }

    public boolean isLeftUser() {
        return !users.isEmpty();
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
