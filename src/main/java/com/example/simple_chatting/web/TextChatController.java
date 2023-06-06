package com.example.simple_chatting.web;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.dto.textMessage.TextMessageRequest;
import com.example.simple_chatting.dto.textMessage.TextMessageResponse;
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
        ChannelType channelType = channelService.getChannelType(request.getChannelId());
        validateTextChannel(channelType);
        textChatService.sendTextMessage(request);
    }

    private void validateTextChannel(ChannelType channelType) {
        if (!ChannelType.TEXT.equals(channelType)) {
            throw new IllegalArgumentException("텍스트 채널이 아닌 채널로는 텍스트 메시지를 보낼 수 없습니다.");
        }
    }
}
