package com.example.simple_chatting.web;

import com.example.simple_chatting.dto.channel.CreateChannelRequest;
import com.example.simple_chatting.dto.channel.CreateChannelResponse;
import com.example.simple_chatting.dto.channel.GetChannelInvitationCodeResponse;
import com.example.simple_chatting.dto.channel.JoinChannelRequest;
import com.example.simple_chatting.security.AccessUser;
import com.example.simple_chatting.security.LoginUser;
import com.example.simple_chatting.service.ChannelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/channels/")
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

    @PutMapping("/{channelId}")
    public ResponseEntity<HttpStatus> joinChannel(
        @LoginUser AccessUser accessUser,
        @PathVariable Long channelId,
        @Valid @RequestBody JoinChannelRequest request
    ) {
        channelService.join(request, accessUser, channelId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{channelId}/invitationCode")
    public GetChannelInvitationCodeResponse getInvitationCode(
        @LoginUser AccessUser accessUser,
        @PathVariable Long channelId) {
        String invitationCode = channelService.getInvitationCode(accessUser, channelId);

        return new GetChannelInvitationCodeResponse().builder()
            .invitationCode(invitationCode)
            .build();
    }

    @PutMapping("/{channelId}/leave")
    public ResponseEntity<HttpStatus> leaveChannel(
        @LoginUser AccessUser accessUser,
        @PathVariable Long channelId
    ) {
        channelService.leave(accessUser, channelId);
        return ResponseEntity.ok().build();
    }
}
