package com.example.simple_chatting.dto.signalling;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {
    @NotNull
    private String content;

    public static Message of(SignallingMessage signallingMessage) {
        return new Message(signallingMessage.getContent());
    }
}
