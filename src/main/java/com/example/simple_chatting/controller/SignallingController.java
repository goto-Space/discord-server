package com.example.simple_chatting.controller;

import com.example.simple_chatting.dto.signalling.AnswerMessage;
import com.example.simple_chatting.dto.signalling.CandidateRequestMessage;
import com.example.simple_chatting.dto.signalling.CandidateResponseMessage;
import com.example.simple_chatting.dto.signalling.Message;
import com.example.simple_chatting.dto.signalling.OfferMessage;
import com.example.simple_chatting.security.Authentication;
import com.example.simple_chatting.security.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SignallingController {

    @MessageMapping("/channels/{channelId}/offer")
    @SendTo("sub/channels/{channelId}")
    public Message sendOfferMessage(
        @Payload @Valid OfferMessage offerMessage,
        @Authentication LoginUser loginUser
    ) {
        return Message.of(offerMessage);
    }

    @MessageMapping("/waiting-channels/{channelId}/answer")
    @SendTo("sub/waiting-channels/{channelId}")
    public Message sendAnswerMessage(
        @Payload @Valid AnswerMessage answerMessage,
        @Authentication LoginUser loginUser
    ) {
        return Message.of(answerMessage);
    }

    @MessageMapping("/channels/{channelId}/candidate-request")
    @SendTo("sub/channels/{channelId}")
    public Message sendCandidateRequestMessage(
        @Payload @Valid CandidateRequestMessage candidateRequestMessage,
        @Authentication LoginUser loginUser
    ) {
        return Message.of(candidateRequestMessage);
    }

    @MessageMapping("/channels/{channelId}/candidate-response")
    @SendTo("sub/waiting-channels/{channelId}")
    public Message sendCandidateResponseMessage(
        @Payload @Valid CandidateResponseMessage candidateResponseMessage,
        @Authentication LoginUser loginUser
    ) {
        return Message.of(candidateResponseMessage);
    }
}
