package com.seconddeal.backend.repository;

//package com.seconddeal.backend.review;

import com.seconddeal.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findBySellerId(Long sellerId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.seller.id = :sellerId")
    Double getAverageRating(@Param("sellerId") Long sellerId);
}