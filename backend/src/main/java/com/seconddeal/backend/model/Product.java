package com.seconddeal.backend.model;

import com.seconddeal.backend.model.Category;
import com.seconddeal.backend.model.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private Double price;

    private String location;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String status = "ACTIVE"; // ACTIVE, SOLD

    @ElementCollection
    private List<String> imageUrls; // store multiple image paths

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    private LocalDateTime createdAt = LocalDateTime.now();
}