package com.seconddeal.backend.controller;

//package com.seconddeal.backend.wishlist;

import com.seconddeal.backend.dto.ProductResponse;
import com.seconddeal.backend.dto.ProductResponse;
import com.seconddeal.backend.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/{productId}")
    public ResponseEntity<String> toggle(
            @PathVariable Long productId,
            Authentication auth) {
        return ResponseEntity.ok(
                wishlistService.toggleWishlist(auth.getName(), productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getWishlist(Authentication auth) {
        return ResponseEntity.ok(wishlistService.getMyWishlist(auth.getName()));
    }
}
