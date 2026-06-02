package com.seconddeal.backend.dto;


import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String location;
    private String category;
    private String status;
    private List<String> imageUrls;
    private String sellerName;
    private String sellerEmail;
    private Long sellerId;
    private LocalDateTime createdAt;
}
