package com.seconddeal.backend.dto;


import lombok.Data;
import java.util.List;

@Data
public class ProductRequest {
    private String title;
    private String description;
    private Double price;
    private String location;
    private String category;
    private List<String> imageUrls;
}
