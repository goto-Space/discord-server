package com.example.simple_chatting.service;

import com.example.simple_chatting.dto.textMessage.TextMessageRequest;
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

    public void sendLeaveTextMessage(
        final Long textChannelId,
        final String senderName) {
        String content = makeLeaveTextContent(senderName);
        TextMessageResponse leaveTextMessageResponse = makeTextMessageResponse(senderName, content);
        sendTextMessageToSpecificChannel(textChannelId, leaveTextMessageResponse);
    }

    public void sendTextMessage(
        TextMessageRequest request
    ) {
        TextMessageResponse textMessageResponse = makeTextMessageResponse(request.getSenderName(), request.getContent());
        sendTextMessageToSpecificChannel(request.getChannelId(), textMessageResponse);

        // TODO : 텍스트 채팅 로그 기록
    }

    private String makeEnterTextContent(String senderName) {
        return senderName + "님이 채팅방에 입장하셨습니다.";
    }

    private TextMessageResponse makeTextMessageResponse(String senderName, String content) {
        return new TextMessageResponse().builder()
            .senderName(senderName)
            .content(content)
            .build();
    }

    private void sendTextMessageToSpecificChannel(Long textChannelId, TextMessageResponse textMessageResponse) {
        simpMessagingTemplate.convertAndSend("/sub/chat/channel/" + textChannelId, textMessageResponse);
    }

    private String makeLeaveTextContent(String senderName) {
        return senderName + "님이 채팅방에서 퇴장하셨습니다.";
    }
}
