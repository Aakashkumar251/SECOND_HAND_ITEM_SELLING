package com.seconddeal.backend.repository;

//package com.seconddeal.backend.chat;

import com.seconddeal.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Get conversation between two users about a product
    @Query("SELECT m FROM Message m WHERE " +
            "m.productId = :productId AND " +
            "((m.sender.id = :user1 AND m.receiver.id = :user2) OR " +
            "(m.sender.id = :user2 AND m.receiver.id = :user1)) " +
            "ORDER BY m.sentAt ASC")
    List<Message> getConversation(
            @Param("productId") Long productId,
            @Param("user1") Long user1,
            @Param("user2") Long user2
    );

    // Get all conversations for a user (inbox)
    @Query("SELECT DISTINCT m FROM Message m WHERE " +
            "m.sender.id = :userId OR m.receiver.id = :userId " +
            "ORDER BY m.sentAt DESC")
    List<Message> getInbox(@Param("userId") Long userId);
}
