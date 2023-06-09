package com.example.simple_chatting.controller;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.dto.channel.CreateChannelRequest;
import com.example.simple_chatting.dto.channel.CreateChannelResponse;
import com.example.simple_chatting.dto.channel.GetChannelInvitationCodeResponse;
import com.example.simple_chatting.dto.channel.JoinChannelRequest;
import com.example.simple_chatting.dto.channel.LeaveChannelRequest;
import com.example.simple_chatting.security.Authentication;
import com.example.simple_chatting.security.LoginUser;
import com.example.simple_chatting.service.ChannelService;
import com.example.simple_chatting.service.TextChatService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channels/")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;
    private final TextChatService textChatService;

    @Operation(
        summary = "채널 생성"
    )
    @PostMapping
    public ResponseEntity<CreateChannelResponse> createChannel(
        @RequestBody @Valid CreateChannelRequest request,
        @Authentication LoginUser loginUser
    ) {
        CreateChannelResponse response = channelService.create(request, loginUser.getId());

        if (ChannelType.TEXT.equals(request.getChannelType())) {
            textChatService.sendEnterTextMessage(response.getChannelId(), loginUser.getName());
        }

        return ResponseEntity
            .created(URI.create("/api/channels/" + response.getChannelId()))
            .body(response);

    }

    @Operation(
        summary = "채널 삭제"
    )
    @DeleteMapping("/{channelId}")
    public ResponseEntity<Void> deleteChannel(
        @PathVariable Long channelId,
        @Authentication LoginUser loginUser
    ) {
        channelService.deleteById(channelId, loginUser.getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "채널 입장"
    )
    @PostMapping("/{channelId}/join")
    public ResponseEntity<Void> joinChannel(
        @PathVariable Long channelId,
        @RequestBody @Valid JoinChannelRequest request,
        @Authentication LoginUser loginUser
    ) {
        channelService.join(request, channelId, loginUser.getId());

        if (ChannelType.TEXT.equals(request.getChannelType())) {
            textChatService.sendEnterTextMessage(channelId, loginUser.getName());
        }

        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "채널 초대 코드 조회"
    )
    @GetMapping("/{channelId}/invitation-code")
    public ResponseEntity<GetChannelInvitationCodeResponse> getInvitationCode(
        @PathVariable Long channelId,
        @Authentication LoginUser loginUser
    ) {
        GetChannelInvitationCodeResponse response = channelService.getInvitationCode(channelId, loginUser.getId());
        return ResponseEntity.ok().body(response);
    }

    @Operation(
        summary = "사용자 채널 퇴장"
    )
    @PostMapping("/{channelId}/release-user")
    public ResponseEntity<Void> releaseUser(
        @PathVariable Long channelId,
        @RequestBody LeaveChannelRequest request,
        @Authentication LoginUser loginUser
    ) {
        channelService.releaseUser(channelId, loginUser.getId());
        if (channelService.existsById(channelId) && ChannelType.TEXT.equals(request.getChannelType())) {
            textChatService.sendLeaveTextMessage(channelId, loginUser.getName());
        }

        return ResponseEntity.ok().build();
    }
}
