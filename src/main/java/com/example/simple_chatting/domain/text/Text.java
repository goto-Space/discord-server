package com.example.simple_chatting.domain.text;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Text {
    private String senderName;
    private String content;
    private String createdAt;
}
