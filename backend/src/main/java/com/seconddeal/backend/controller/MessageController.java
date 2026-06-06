package com.seconddeal.backend.controller;

//package com.seconddeal.backend.chat;

import com.seconddeal.backend.dto.MessageRequest;
import com.seconddeal.backend.dto.MessageResponse;
import com.seconddeal.backend.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> send(
            @RequestBody MessageRequest request,
            Authentication auth) {
        return ResponseEntity.ok(
                messageService.sendMessage(auth.getName(), request));
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<MessageResponse>> conversation(
            @RequestParam Long productId,
            @RequestParam Long otherUserId,
            Authentication auth) {
        return ResponseEntity.ok(
                messageService.getConversation(auth.getName(), productId, otherUserId));
    }

    @GetMapping("/inbox")
    public ResponseEntity<List<MessageResponse>> inbox(Authentication auth) {
        return ResponseEntity.ok(messageService.getInbox(auth.getName()));
    }
}