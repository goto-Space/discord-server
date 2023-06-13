package com.example.simple_chatting.domain.channel;

import com.example.simple_chatting.common.ChannelType;
import java.util.LinkedList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoChannel extends Channel {
    private List<WebSocketSession> clientSessions;

    @Builder
    public VideoChannel(ChannelType type, String name, Long hostUserId) {
        super(type, name, hostUserId);
        clientSessions = new LinkedList<>();
    }

    public void addClientSession(WebSocketSession session) {
        clientSessions.add(session);
    }
}
