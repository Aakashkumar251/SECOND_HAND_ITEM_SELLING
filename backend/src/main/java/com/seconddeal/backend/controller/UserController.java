package com.seconddeal.backend.controller;

import com.seconddeal.backend.dto.ProfileResponse;
import com.seconddeal.backend.dto.UpdateProfileRequest;
import com.seconddeal.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {
    private final UserService userService;

    // Get my profile
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(Authentication auth) {
        return ResponseEntity.ok(userService.getProfile(auth.getName()));
    }

    // Update my profile
    @PutMapping("/profile")
    public ResponseEntity<ProfileResponse> updateProfile(
            @RequestBody UpdateProfileRequest request,
            Authentication auth) {
        return ResponseEntity.ok(userService.updateProfile(auth.getName(), request));
    }
}
