package com.example.simple_chatting.dto.textMessage;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TextMessageRequest {
    @NotEmpty
    private String senderName;
    @NotNull
    private Long channelId;
    @NotEmpty
    private String content;
}
