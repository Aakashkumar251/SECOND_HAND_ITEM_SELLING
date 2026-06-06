package com.seconddeal.backend.service;

//package com.seconddeal.backend.review;

import com.seconddeal.backend.dto.ReviewRequest;
import com.seconddeal.backend.dto.ReviewResponse;
import com.seconddeal.backend.model.Review;
import com.seconddeal.backend.repository.ReviewRepository;
import com.seconddeal.backend.model.User;
import com.seconddeal.backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepo userRepository;

    public ReviewResponse addReview(String reviewerEmail, ReviewRequest request) {
        User reviewer = userRepository.findByEmail(reviewerEmail);
        User seller = userRepository.findById(request.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Review review = new Review();
        review.setReviewer(reviewer);
        review.setSeller(seller);
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        return toResponse(reviewRepository.save(review));
    }

    public List<ReviewResponse> getSellerReviews(Long sellerId) {
        return reviewRepository.findBySellerId(sellerId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Double getAverageRating(Long sellerId) {
        Double avg = reviewRepository.getAverageRating(sellerId);
        return avg != null ? avg : 0.0;
    }

    private ReviewResponse toResponse(Review r) {
        ReviewResponse res = new ReviewResponse();
        res.setId(r.getId());
        res.setReviewerName(r.getReviewer().getName());
        res.setRating(r.getRating());
        res.setComment(r.getComment());
        res.setCreatedAt(r.getCreatedAt());
        return res;
    }
}