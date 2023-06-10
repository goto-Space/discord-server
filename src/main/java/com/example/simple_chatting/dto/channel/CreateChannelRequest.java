package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.common.ChannelType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateChannelRequest {
    @NotNull
    private ChannelType channelType;
    @NotEmpty
    private String channelName;
}

