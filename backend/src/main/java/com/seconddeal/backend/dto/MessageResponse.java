package com.seconddeal.backend.dto;

//package com.seconddeal.backend.chat;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageResponse {
    private Long id;
    private String senderName;
    private String receiverName;
    private Long senderId;
    private Long receiverId;
    private Long productId;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
}
