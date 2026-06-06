package com.seconddeal.backend.controller;

//package com.seconddeal.backend.admin;

import com.seconddeal.backend.repository.ProductRepository;
import com.seconddeal.backend.dto.ProductResponse;
import com.seconddeal.backend.service.ProductService;
import com.seconddeal.backend.repository.ProductRepository;
import com.seconddeal.backend.model.User;
import com.seconddeal.backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final UserRepo userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    // Dashboard stats
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalProducts", productRepository.count());
        stats.put("activeProducts",
                productRepository.findByStatus("ACTIVE").size());
        stats.put("soldProducts",
                productRepository.findByStatus("SOLD").size());
        return ResponseEntity.ok(stats);
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // Delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }

    // Get all products
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Delete any product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }
}