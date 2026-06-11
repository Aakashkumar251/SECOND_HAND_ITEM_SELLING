package com.seconddeal.backend.controller;

//package com.seconddeal.backend.product;

import com.seconddeal.backend.dto.ProductRequest;
import com.seconddeal.backend.dto.ProductResponse;
import com.seconddeal.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    // Anyone can see all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Anyone can see one product
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {  /// GET /api/products/5
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Anyone can search
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> search(@RequestParam String keyword) {  //Used when data comes after ? in the URL.
        return ResponseEntity.ok(productService.searchProducts(keyword));              // GET /search?category=Electronics&maxPrice=50000
    }

    // Only logged in users can post
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody ProductRequest request,
            Authentication authentication) {
        String email = authentication.getName(); // gets email from JWT
        return ResponseEntity.ok(productService.createProduct(request, email));
    }

    // Get my own listings
    @GetMapping("/my-listings")
    public ResponseEntity<List<ProductResponse>> myListings(Authentication authentication) {
        return ResponseEntity.ok(productService.getMyProducts(authentication.getName()));
    }

//    // Mark as sold
//    @PutMapping("/{id}/sold")
//    public ResponseEntity<ProductResponse> markSold(@PathVariable Long id) {
//        return ResponseEntity.ok(productService.updateStatus(id));
//    }
@PutMapping("/{id}/sold")
public ResponseEntity<ProductResponse> updateStatus(
        @PathVariable Long id,

        Authentication authentication) {

    String email = authentication.getName();

    return ResponseEntity.ok(
            productService.updateStatus(id, email));
}


    @PutMapping("/{id}/active")
    public ResponseEntity<ProductResponse> updateStatusactive(
            @PathVariable Long id,

            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                productService.updateStatusactive(id, email));
    }





    // Delete listing
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponse>> filter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String location) {

        return ResponseEntity.ok(
                productService.filterProducts(category, minPrice, maxPrice, location));
    }
}