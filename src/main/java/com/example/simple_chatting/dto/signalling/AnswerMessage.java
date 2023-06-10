package com.example.simple_chatting.dto.signalling;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerMessage implements SignallingMessage {
    @NotNull
    private String content;
}
