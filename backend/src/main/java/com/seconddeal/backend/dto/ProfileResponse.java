package com.seconddeal.backend.dto;

//package com.seconddeal.backend.user;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProfileResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String profileImage;
    private LocalDateTime createdAt;
}