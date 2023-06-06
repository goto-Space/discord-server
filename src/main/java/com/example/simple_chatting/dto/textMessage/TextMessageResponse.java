package com.example.simple_chatting.dto.textMessage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextMessageResponse {
    private String senderName;
    private String content;

    @Builder
    public TextMessageResponse(String senderName, String content) {
        this.senderName = senderName;
        this.content = content;
    }
}
