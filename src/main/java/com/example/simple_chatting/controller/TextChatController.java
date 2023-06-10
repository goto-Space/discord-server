package com.example.simple_chatting.controller;

import com.example.simple_chatting.dto.textMessage.TextMessageRequest;
import com.example.simple_chatting.service.ChannelService;
import com.example.simple_chatting.service.TextChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TextChatController {

    private final TextChatService textChatService;
    private final ChannelService channelService;

    @MessageMapping("/chat/text")
    public void sendMessage(@Payload TextMessageRequest request) {
        textChatService.sendTextMessage(request);
    }
}
