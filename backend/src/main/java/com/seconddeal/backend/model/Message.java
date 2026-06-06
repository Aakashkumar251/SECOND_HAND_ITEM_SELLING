package com.seconddeal.backend.model;

//package com.seconddeal.backend.chat;

import com.seconddeal.backend.model.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private Long productId;

    @Column(length = 1000)
    private String content;

    private LocalDateTime sentAt = LocalDateTime.now();

    private boolean isRead = false;
}
