package com.example.simple_chatting.dto.textMessage;

import com.example.simple_chatting.common.ChannelType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TextMessageRequest {
    @NotNull
    private Long userId;
    @NotEmpty
    private String senderName;
    @NotNull
    private ChannelType channelType;
    @NotNull
    private Long channelId;
    @NotEmpty
    private String content;
}
