package com.example.simple_chatting.controller;

import com.example.simple_chatting.domain.channel.VideoChannel;
import com.example.simple_chatting.domain.user.User;
import com.example.simple_chatting.dto.channel.FindChannelResponse;
import com.example.simple_chatting.dto.signalling.WebSocketMessage;
import com.example.simple_chatting.repository.ChannelRepository;
import com.example.simple_chatting.repository.UserRepository;
import com.example.simple_chatting.service.ChannelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class SignalHandler extends TextWebSocketHandler {

    // message types, used in signalling:
    // SDP Offer message
    private static final String MSG_TYPE_OFFER = "offer";
    // SDP Answer message
    private static final String MSG_TYPE_ANSWER = "answer";
    // New ICE Candidate message
    private static final String MSG_TYPE_ICE = "ice";
    // join room data message
    private static final String MSG_TYPE_JOIN = "join";
    // leave room data message
    private static final String MSG_TYPE_LEAVE = "leave";

    private final ChannelService channelService;

    private final ChannelRepository channelRepository;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // 연결 끊어졌을 때 이벤트처리
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    }

    // 소켓 연결되었을 때 이벤트 처리
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sendMessage(session, new WebSocketMessage(-1L, MSG_TYPE_JOIN, -1L, null, null));
    }

    // 소켓 메시지 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        // a message has been received
        try {
            WebSocketMessage message = objectMapper.readValue(textMessage.getPayload(), WebSocketMessage.class);
            Long senderId = message.getSenderId(); // 유저 id
            Long channelId = message.getChannelId(); // channelId

            switch (message.getType()) {
                case MSG_TYPE_OFFER:
                case MSG_TYPE_ANSWER:
                case MSG_TYPE_ICE:
                    Object candidate = message.getCandidate();
                    Object sdp = message.getSdp();

                    FindChannelResponse response = channelService.findById(channelId, senderId);

                    if (response == null)
                        break;

                    List<WebSocketSession> sessions = channelService.getChannelClientSessions(channelId);

                    for (WebSocketSession webSocketSession : sessions) {
                        if (!webSocketSession.getId().equals(session.getId()))
                            sendMessage(session, new WebSocketMessage(senderId, message.getType(), channelId, candidate, sdp));
                    }
                    break;
                case MSG_TYPE_JOIN:

                    VideoChannel channel = (VideoChannel) channelRepository.findById(channelId);
                    User user = userRepository.findById(senderId);
                    channel.join(user);
                    channel.addClientSession(session);
                    break;
                default:
                    break;
            }

        } catch (IOException e) {

        }
    }

    private void sendMessage(WebSocketSession session, WebSocketMessage message) {
        try {
            String json = objectMapper.writeValueAsString(message);
            session.sendMessage(new TextMessage(json));
        } catch (IOException e) {

        }
    }
}