package com.example.simple_chatting.service;

import com.example.simple_chatting.common.ChannelType;
import com.example.simple_chatting.dto.chatRoom.CreateChannelRequest;
import com.example.simple_chatting.repository.ChannelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {

    @Autowired ChannelService channelService;

    @Autowired ChannelRepository channelRepository;

    @AfterEach
    void after() {
        channelRepository.clear();
    }

    @Test
    @DisplayName("텍스트 채널 생성 테스트")
    void createTextChannel() {
        // given
        String channelName1 = "텍스트 채널1";
        CreateChannelRequest request = new CreateChannelRequest().builder()
            .type(ChannelType.TEXT)
            .name(channelName1)
            .build();

        // when
        Long channelId = channelService.createChannel(request);

        // then
        assertEquals(channelId, channelRepository.findById(channelId).getId());
    }

    @Test
    @DisplayName("음성 채널 생성 테스트")
    void createVoiceOnlyChannel() {
        // given
        String channelName1 = "음성 채널1";
        CreateChannelRequest request = new CreateChannelRequest().builder()
            .type(ChannelType.VOICE_ONLY)
            .name(channelName1)
            .build();

        // when
        Long channelId = channelService.createChannel(request);

        // then
        assertEquals(channelId, channelRepository.findById(channelId).getId());
    }

    @Test
    @DisplayName("화상 채널 생성 테스트")
    void createVideoChannel() {
        // given
        String channelName1 = "화상 채널1";
        CreateChannelRequest request = new CreateChannelRequest().builder()
            .type(ChannelType.VIDEO)
            .name(channelName1)
            .build();

        // when
        Long channelId = channelService.createChannel(request);

        // then
        assertEquals(channelId, channelRepository.findById(channelId).getId());
    }

    @Test
    @DisplayName("그룹 내 같은 채널 안에서는 서로 이름이 달라야한다.")
    void checkDuplicateChannelName() {
        // given
        String channelName1 = "화상 채널1";
        String channelName2 = "화상 채널1";
        CreateChannelRequest request1 = new CreateChannelRequest().builder()
            .type(ChannelType.VIDEO)
            .name(channelName1)
            .build();

        CreateChannelRequest request2 = new CreateChannelRequest().builder()
            .type(ChannelType.VIDEO)
            .name(channelName2)
            .build();

        // when
        channelService.createChannel(request1);

        // then
        assertThrows(IllegalArgumentException.class, () -> channelService.createChannel(request2));
    }
}