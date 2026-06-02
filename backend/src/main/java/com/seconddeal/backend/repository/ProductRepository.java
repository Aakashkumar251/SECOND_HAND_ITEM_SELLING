package com.seconddeal.backend.repository;

import com.seconddeal.backend.model.Category;
import com.seconddeal.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // Get all products by a seller
    List<Product> findBySellerId(Long sellerId);

    // Get all active products
    List<Product> findByStatus(String status);

    // Search by title containing a word
    List<Product> findByTitleContainingIgnoreCase(String keyword);

    // Filter by category
    List<Product> findByCategoryAndStatus(Category category, String status);
}
