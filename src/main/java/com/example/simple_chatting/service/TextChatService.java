package com.example.simple_chatting.service;

import com.example.simple_chatting.domain.channel.TextChannel;
import com.example.simple_chatting.domain.text.Text;
import com.example.simple_chatting.dto.textMessage.TextMessageRequest;
import com.example.simple_chatting.dto.textMessage.TextMessageResponse;
import com.example.simple_chatting.repository.ChannelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChannelRepository channelRepository;

    public void sendEnterTextMessage(
        final Long textChannelId,
        final String senderName
    ) {
        TextChannel channel = (TextChannel) channelRepository.findById(textChannelId);
        String content = makeEnterTextContent(senderName);
        channel.addTextLog(new Text(senderName, content, ""));
        TextMessageResponse enterTextMessageResponse = makeTextMessageResponse(channel.getTextLogs());
        sendTextMessageToSpecificChannel(textChannelId, enterTextMessageResponse);
    }

    public void sendLeaveTextMessage(
        final Long textChannelId,
        final String senderName
    ) {
        TextChannel channel = (TextChannel) channelRepository.findById(textChannelId);
        String content = makeLeaveTextContent(senderName);
        channel.addTextLog(new Text(senderName, content, ""));
        TextMessageResponse leaveTextMessageResponse = makeTextMessageResponse(channel.getTextLogs());
        sendTextMessageToSpecificChannel(textChannelId, leaveTextMessageResponse);
    }

    public void sendTextMessage(
        TextMessageRequest request
    ) {
        TextChannel channel = (TextChannel) channelRepository.findById(request.getChannelId());
        channel.addTextLog(new Text(request.getSenderName(), request.getContent(), request.getCreatedAt()));
        TextMessageResponse textMessageResponse = makeTextMessageResponse(channel.getTextLogs());
        sendTextMessageToSpecificChannel(request.getChannelId(), textMessageResponse);
    }

    private String makeEnterTextContent(String senderName) {
        return senderName + "님이 채팅방에 입장하셨습니다.";
    }

    private TextMessageResponse makeTextMessageResponse(List<Text> textLogResponseLogs) {
        return TextMessageResponse.of(textLogResponseLogs);
    }

    private void sendTextMessageToSpecificChannel(Long textChannelId, TextMessageResponse textMessageResponse) {
        simpMessagingTemplate.convertAndSend("/sub/channels/" + textChannelId, textMessageResponse);
    }

    private String makeLeaveTextContent(String senderName) {
        return senderName + "님이 채팅방에서 퇴장하셨습니다.";
    }
}
