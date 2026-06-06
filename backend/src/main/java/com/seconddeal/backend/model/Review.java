package com.seconddeal.backend.model;

//package com.seconddeal.backend.review;

//import com.seconddeal.backend.user.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    private int rating; // 1 to 5

    private String comment;

    private LocalDateTime createdAt = LocalDateTime.now();
}
