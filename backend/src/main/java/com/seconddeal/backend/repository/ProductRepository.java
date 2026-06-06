package com.seconddeal.backend.repository;

import com.seconddeal.backend.model.Category;
import com.seconddeal.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    // Filter by category and price range
    @Query("SELECT p FROM Product p WHERE " +
            "(:category IS NULL OR p.category = :category) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:location IS NULL OR LOWER(p.location) LIKE LOWER(CONCAT('%',:location,'%'))) AND " +
            "p.status = 'ACTIVE'")
    List<Product> filterProducts(
            @Param("category") Category category,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("location") String location
    );
}
