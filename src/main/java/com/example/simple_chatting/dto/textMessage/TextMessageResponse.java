package com.example.simple_chatting.dto.textMessage;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TextMessageResponse {
    @NotEmpty
    private String senderName;
    @NotEmpty
    private String content;
}
