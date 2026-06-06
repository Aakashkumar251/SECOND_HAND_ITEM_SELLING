package com.seconddeal.backend.dto;

//package com.seconddeal.backend.chat;

import lombok.Data;

@Data
public class MessageRequest {
    private Long receiverId;
    private Long productId;
    private String content;
}