package com.example.simple_chatting.dto.channel;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindAllChannelResponse {
    private List<FindChannelResponse> channels;

    public static FindAllChannelResponse of(List<FindChannelResponse> findChannelResponses) {
        return new FindAllChannelResponse(findChannelResponses);
    }
}
