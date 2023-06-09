package com.example.simple_chatting.controller;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.dto.channel.CreateChannelRequest;
import com.example.simple_chatting.dto.channel.CreateChannelResponse;
import com.example.simple_chatting.dto.channel.GetChannelInvitationCodeResponse;
import com.example.simple_chatting.dto.channel.JoinChannelRequest;
import com.example.simple_chatting.dto.channel.LeaveChannelRequest;
import com.example.simple_chatting.security.AccessUser;
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
    public ResponseEntity<Void> createChannel(
        @RequestBody @Valid CreateChannelRequest request,
        @LoginUser AccessUser accessUser
    ) {
        CreateChannelResponse response = channelService.create(request, accessUser.getId());

        if (ChannelType.TEXT.equals(request.getChannelType())) {
            textChatService.sendEnterTextMessage(response.getChannelId(), accessUser.getName());
        }

        return ResponseEntity
            .created(URI.create("/api/channels/" + response.getChannelId()))
            .build();
    }

    @Operation(
        summary = "채널 삭제"
    )
    @DeleteMapping("/{channelId}")
    public ResponseEntity<Void> deleteChannel(
        @PathVariable Long channelId,
        @LoginUser AccessUser accessUser
    ) {
        channelService.deleteById(channelId, accessUser.getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "채널 입장"
    )
    @PostMapping("/{channelId}/join")
    public ResponseEntity<Void> joinChannel(
        @PathVariable Long channelId,
        @RequestBody @Valid JoinChannelRequest request,
        @LoginUser AccessUser accessUser
    ) {
        channelService.join(request, channelId, accessUser.getId());

        if (ChannelType.TEXT.equals(request.getChannelType())) {
            textChatService.sendEnterTextMessage(channelId, accessUser.getName());
        }

        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "채널 초대 코드 조회"
    )
    @GetMapping("/{channelId}/invitation-code")
    public ResponseEntity<GetChannelInvitationCodeResponse> getInvitationCode(
        @PathVariable Long channelId,
        @LoginUser AccessUser accessUser
    ) {
        GetChannelInvitationCodeResponse response = channelService.getInvitationCode(channelId, accessUser.getId());
        return ResponseEntity.ok().body(response);
    }

    @Operation(
        summary = "사용자 채널 퇴장"
    )
    @PostMapping("/{channelId}/release-user")
    public ResponseEntity<Void> releaseUser(
        @PathVariable Long channelId,
        @RequestBody LeaveChannelRequest request,
        @LoginUser AccessUser accessUser
    ) {
        channelService.releaseUser(channelId, accessUser.getId());
        if (channelService.exist(channelId) && ChannelType.TEXT.equals(request.getChannelType())) {
            textChatService.sendLeaveTextMessage(channelId, accessUser.getName());
        }

        return ResponseEntity.ok().build();
    }
}
