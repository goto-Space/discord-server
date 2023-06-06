package com.example.simple_chatting.dto.textMessage;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextMessageRequest {
    private Long userId;
    @NotEmpty
    private String senderName;
    private Long channelId;
    @NotEmpty
    private String content;

    @Builder
    public TextMessageRequest(Long userId, String senderName, Long channelId, String content) {
        this.userId = userId;
        this.senderName = senderName;
        this.channelId = channelId;
        this.content = content;
    }
}
