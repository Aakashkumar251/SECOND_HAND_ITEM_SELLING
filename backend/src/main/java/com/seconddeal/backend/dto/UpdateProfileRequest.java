package com.seconddeal.backend.dto;

//package com.seconddeal.backend.user;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String name;
    private String phone;
    private String profileImage;
}
