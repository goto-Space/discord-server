package com.example.simple_chatting.web;

import com.example.simple_chatting.dto.chatRoom.CreateChannelRequest;
import com.example.simple_chatting.dto.chatRoom.CreateChannelResponse;
import com.example.simple_chatting.security.AccessUser;
import com.example.simple_chatting.security.LoginUser;
import com.example.simple_chatting.service.ChannelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    public CreateChannelResponse createChannel(
        @LoginUser AccessUser accessUser,
        @Valid @RequestBody CreateChannelRequest request) {
        Long channelId = channelService.createChannel(request, accessUser);

        return new CreateChannelResponse().builder()
            .channelId(channelId)
            .build();
    }

    @DeleteMapping("/{channelId}")
    public ResponseEntity<HttpStatus> deleteChannel(
        @LoginUser AccessUser accessUser,
        @PathVariable Long channelId) {
        channelService.deleteById(channelId, accessUser);
        return ResponseEntity.ok().build();
    }

}
