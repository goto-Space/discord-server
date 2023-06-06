package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.common.ChannelType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateChannelRequest {
    private ChannelType type;
    @NotEmpty
    private String name;

    @Builder
    public CreateChannelRequest(ChannelType type, String name) {
        this.type = type;
        this.name = name;
    }
}
