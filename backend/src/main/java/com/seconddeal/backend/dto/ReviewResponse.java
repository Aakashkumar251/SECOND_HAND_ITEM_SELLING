package com.seconddeal.backend.dto;

//package com.seconddeal.backend.review;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewResponse {
    private Long id;
    private String reviewerName;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
