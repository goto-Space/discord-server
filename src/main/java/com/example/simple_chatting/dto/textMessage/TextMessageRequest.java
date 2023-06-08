package com.example.simple_chatting.dto.textMessage;

import com.example.simple_chatting.common.ChannelType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TextMessageRequest {
    private Long userId;
    @NotEmpty
    private String senderName;
    private ChannelType channelType;
    private Long channelId;
    @NotEmpty
    private String content;
}
