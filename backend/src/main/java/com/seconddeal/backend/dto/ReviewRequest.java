package com.seconddeal.backend.dto;

//package com.seconddeal.backend.review;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long sellerId;
    private int rating;
    private String comment;
}