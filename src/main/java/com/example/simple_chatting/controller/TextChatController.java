package com.example.simple_chatting.controller;

import com.example.simple_chatting.dto.textMessage.TextMessageRequest;
import com.example.simple_chatting.security.Authentication;
import com.example.simple_chatting.security.LoginUser;
import com.example.simple_chatting.service.TextChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TextChatController {
    private final TextChatService textChatService;

    @MessageMapping("/text")
    public void sendTextMessage(
        @Payload @Valid TextMessageRequest request,
        @Authentication LoginUser loginUser
    ) {
        textChatService.sendTextMessage(request);
    }
}
