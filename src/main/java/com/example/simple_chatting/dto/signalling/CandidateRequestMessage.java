package com.example.simple_chatting.dto.signalling;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CandidateRequestMessage implements SignallingMessage {
    @NotNull
    private String content;
}
