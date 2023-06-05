package com.example.simple_chatting.domain.channel;

import com.example.simple_chatting.common.ChannelType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoChannel extends Channel {
    @Builder
    public VideoChannel(ChannelType type, String name) {
        super(type, name);
    }
}
