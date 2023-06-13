package com.example.simple_chatting.dto.signalling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// WebRTC 연결 시 사용되는 클래스
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMessage {
    private Long senderId; // 보내는 유저 UUID
    private String type; // 메시지 타입
    private Long channelId;
    private Object candidate; // 상태
    private Object sdp; // sdp 정보
}