package com.example.simple_chatting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
//class ChannelServiceTest {
//
//    @Autowired ChannelService channelService;
//
//    @Autowired ChannelRepository channelRepository;
//
//    @Autowired UserService userService;
//
//    @Autowired UserRepository userRepository;
//
//    AccessUser accessUser;
//
//    @BeforeEach
//    void before() {
//        String name = "sonny";
//        String loginId = "network-protocol";
//        String password = "2018";
//        RegisterUserRequest registerUserRequest = new RegisterUserRequest(name, loginId, password);
//        userService.join(registerUserRequest);
//
//        LoginUserRequest loginUserRequest = new LoginUserRequest(loginId, password);
//        accessUser = userService.login(loginUserRequest);
//
//        userSessionManager.saveUser(accessUser);
//    }
//
//    @AfterEach
//    void after() {
//        userRepository.clear();
//        channelRepository.clear();
//    }
//
//    @Test
//    @DisplayName("텍스트 채널 생성 테스트")
//    void createTextChannel() {
//        // given
//        String channelName1 = "텍스트 채널1";
//        CreateChannelRequest request = new CreateChannelRequest().builder()
//            .type(ChannelType.TEXT)
//            .name(channelName1)
//            .build();
//
//        // when
//        Long channelId = channelService.createChannel(request, accessUser);
//
//        // then
//        assertEquals(channelId, channelRepository.findById(channelId).getId());
//    }
//
//    @Test
//    @DisplayName("음성 채널 생성 테스트")
//    void createVoiceOnlyChannel() {
//        // given
//        String channelName1 = "음성 채널1";
//        CreateChannelRequest request = new CreateChannelRequest().builder()
//            .type(ChannelType.VOICE_ONLY)
//            .name(channelName1)
//            .build();
//
//        // when
//        Long channelId = channelService.createChannel(request, accessUser);
//
//        // then
//        assertEquals(channelId, channelRepository.findById(channelId).getId());
//    }
//
//    @Test
//    @DisplayName("화상 채널 생성 테스트")
//    void createVideoChannel() {
//        // given
//        String channelName1 = "화상 채널1";
//        CreateChannelRequest request = new CreateChannelRequest().builder()
//            .type(ChannelType.VIDEO)
//            .name(channelName1)
//            .build();
//
//        // when
//        Long channelId = channelService.createChannel(request, accessUser);
//
//        // then
//        assertEquals(channelId, channelRepository.findById(channelId).getId());
//    }
//
//    @Test
//    @DisplayName("그룹 내 같은 채널 안에서는 서로 이름이 달라야한다.")
//    void checkDuplicateChannelName() {
//        // given
//        String channelName1 = "화상 채널1";
//        String channelName2 = "화상 채널1";
//        CreateChannelRequest request1 = new CreateChannelRequest().builder()
//            .type(ChannelType.VIDEO)
//            .name(channelName1)
//            .build();
//
//        CreateChannelRequest request2 = new CreateChannelRequest().builder()
//            .type(ChannelType.VIDEO)
//            .name(channelName2)
//            .build();
//
//        // when
//        channelService.createChannel(request1, accessUser);
//
//        // then
//        assertThrows(IllegalArgumentException.class, () -> channelService.createChannel(request2, accessUser));
//    }
//
//
//    @Test
//    @DisplayName("채널 생성 후 삭제 테스트")
//    void deleteChannel() {
//        // given
//        String channelName1 = "화상 채널1";
//        CreateChannelRequest request1 = new CreateChannelRequest().builder()
//            .type(ChannelType.VIDEO)
//            .name(channelName1)
//            .build();
//
//        // when
//        Long channelId = channelService.createChannel(request1, accessUser);
//        channelService.deleteById(channelId, accessUser);
//
//        // then
//        assertNull(channelRepository.findById(channelId));
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 채널은 삭제할 수 없다.")
//    void deleteInvalidChannel() {
//        // given
//        String channelName1 = "화상 채널1";
//        CreateChannelRequest request1 = new CreateChannelRequest().builder()
//            .type(ChannelType.VIDEO)
//            .name(channelName1)
//            .build();
//
//        // when
//        Long channelId = channelService.createChannel(request1, accessUser);
//        channelService.deleteById(channelId, accessUser);
//
//        // then
//        assertThrows(IllegalArgumentException.class, () -> channelService.deleteById(channelId, accessUser));
//    }
//
//    @Test
//    @DisplayName("방장만 채널을 삭제할 수 있다.")
//    void deleteChannelOnlyHost() {
//        // given
//        String name = "shw";
//        String loginId = "loginHi";
//        String password = "passwordHi";
//        RegisterUserRequest registerUserRequest = new RegisterUserRequest(name, loginId, password);
//        userService.join(registerUserRequest);
//
//        LoginUserRequest loginUserRequest = new LoginUserRequest(loginId, password);
//        AccessUser anotherAccessUser = userService.login(loginUserRequest);
//        userSessionManager.saveUser(anotherAccessUser);
//
//        String channelName1 = "화상 채널1";
//        CreateChannelRequest request1 = new CreateChannelRequest().builder()
//            .type(ChannelType.VIDEO)
//            .name(channelName1)
//            .build();
//
//        // when
//        Long channelId = channelService.createChannel(request1, accessUser);
//
//        // then
//        assertThrows(IllegalArgumentException.class, () -> channelService.deleteById(channelId, anotherAccessUser));
//    }
//
//}