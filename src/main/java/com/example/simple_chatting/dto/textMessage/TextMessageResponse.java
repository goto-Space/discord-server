package com.example.simple_chatting.dto.textMessage;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextMessageResponse {
    @NotEmpty
    private String senderName;
    @NotEmpty
    private String content;

    @Builder
    public TextMessageResponse(String senderName, String content) {
        this.senderName = senderName;
        this.content = content;
    }
}
