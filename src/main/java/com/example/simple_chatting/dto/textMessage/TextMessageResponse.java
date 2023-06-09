package com.example.simple_chatting.dto.textMessage;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TextMessageResponse {
    @NotEmpty
    private String senderName;
    @NotEmpty
    private String content;

    public static TextMessageResponse of(String senderName, String content) {
        return new TextMessageResponse(senderName, content);
    }
}
