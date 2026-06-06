package com.seconddeal.backend.service;

//package com.seconddeal.backend.chat;

import com.seconddeal.backend.dto.MessageRequest;
import com.seconddeal.backend.dto.MessageResponse;
import com.seconddeal.backend.model.Message;
import com.seconddeal.backend.repository.MessageRepository;
import com.seconddeal.backend.model.User;
import com.seconddeal.backend.repository.UserRepo;
import com.seconddeal.backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepo userRepository;

    public MessageResponse sendMessage(String senderEmail, MessageRequest request) {
        User sender = userRepository.findByEmail(senderEmail);
        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setProductId(request.getProductId());
        message.setContent(request.getContent());

        return toResponse(messageRepository.save(message));
    }

    public List<MessageResponse> getConversation(
            String email, Long productId, Long otherUserId) {
        User user = userRepository.findByEmail(email);
        return messageRepository
                .getConversation(productId, user.getId(), otherUserId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<MessageResponse> getInbox(String email) {
        User user = userRepository.findByEmail(email);
        return messageRepository.getInbox(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MessageResponse toResponse(Message m) {
        MessageResponse res = new MessageResponse();
        res.setId(m.getId());
        res.setSenderName(m.getSender().getName());
        res.setReceiverName(m.getReceiver().getName());
        res.setSenderId(m.getSender().getId());
        res.setReceiverId(m.getReceiver().getId());
        res.setProductId(m.getProductId());
        res.setContent(m.getContent());
        res.setSentAt(m.getSentAt());
        res.setRead(m.isRead());
        return res;
    }
}