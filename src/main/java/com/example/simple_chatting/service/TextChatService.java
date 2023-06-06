package com.example.simple_chatting.service;

import com.example.simple_chatting.domain.user.User;
import com.example.simple_chatting.dto.textMessage.TextMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendEnterTextMessage(
        final Long textChannelId,
        final String senderName) {
        String content = makeEnterTextContent(senderName);
        TextMessageResponse enterTextMessageResponse = makeTextMessageResponse(senderName, content);
        sendTextMessageToSpecificChannel(textChannelId, enterTextMessageResponse);
    }

    private String makeEnterTextContent(String senderName) {
        return senderName + "님이 채팅방에 입장하셨습니다.";
    }

    private TextMessageResponse makeTextMessageResponse(String senderName, String content) {
        TextMessageResponse enterTextMessageResponse = new TextMessageResponse().builder()
            .senderName(senderName)
            .content(content)
            .build();
        return enterTextMessageResponse;
    }

    private void sendTextMessageToSpecificChannel(Long textChannelId, TextMessageResponse textMessageResponse) {
        simpMessagingTemplate.convertAndSend("/sub/chat/channel/" + textChannelId, textMessageResponse);
    }

    private String makeLeaveTextContent(String senderName) {
        return senderName + "님이 채팅방에서 퇴장하셨습니다.";
    }
}
