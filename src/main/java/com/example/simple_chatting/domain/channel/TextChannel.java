package com.example.simple_chatting.domain.channel;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.domain.text.Text;
import java.util.LinkedList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextChannel extends Channel {
    private List<Text> textLogs;

    @Builder
    public TextChannel(ChannelType type, String name, Long hostUserId) {
        super(type, name, hostUserId);
        textLogs = new LinkedList<>();
    }

    public void addTextLog(Text text) {
        textLogs.add(text);
    }

    public List<Text> getTextLogs() {
        return textLogs;
    }
}
