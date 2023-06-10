package com.example.simple_chatting.dto.channel;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.channel.Channel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindChannelResponse {
    private Long id;
    private ChannelType type;
    private String name;
    private List<Long> userIds;
    private Long hostUserId;

    public static FindChannelResponse of(Channel channel) {
        return new FindChannelResponse(
            channel.getId(),
            channel.getType(),
            channel.getName(),
            channel.getUsers().stream().map(it -> it.getId()).collect(Collectors.toList()),
            channel.getHostUserId()
        );
    }
}
