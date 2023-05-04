package com.example.simple_chatting.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public class SocketVO {
    private String userName;
    private String content;
}
