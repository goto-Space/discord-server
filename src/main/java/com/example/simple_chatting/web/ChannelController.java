package com.example.simple_chatting.web;

import com.example.simple_chatting.dto.chatRoom.CreateChannelRequest;
import com.example.simple_chatting.dto.chatRoom.CreateChannelResponse;
import com.example.simple_chatting.service.ChannelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/channel")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping("/create")
    public CreateChannelResponse createChannel(@Valid @RequestBody CreateChannelRequest request) {
        Long channelId = channelService.createChannel(request);

        return new CreateChannelResponse().builder()
            .channelId(channelId)
            .build();
    }


}
