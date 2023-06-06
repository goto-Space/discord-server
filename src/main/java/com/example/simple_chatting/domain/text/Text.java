package com.example.simple_chatting.domain.text;

import com.example.simple_chatting.domain.user.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Text {
    // TODO: 채팅 로그 기록시 리팩토링 고려

    private Long id;

    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    private String content;
}
