package com.seconddeal.backend.controller;

//package com.seconddeal.backend.review;

import com.seconddeal.backend.dto.ReviewRequest;
import com.seconddeal.backend.dto.ReviewResponse;
import com.seconddeal.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(
            @RequestBody ReviewRequest request,
            Authentication auth) {
        return ResponseEntity.ok(
                reviewService.addReview(auth.getName(), request));
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ReviewResponse>> getReviews(
            @PathVariable Long sellerId) {
        return ResponseEntity.ok(reviewService.getSellerReviews(sellerId));
    }

    @GetMapping("/seller/{sellerId}/rating")
    public ResponseEntity<Double> getRating(@PathVariable Long sellerId) {
        return ResponseEntity.ok(reviewService.getAverageRating(sellerId));
    }
}