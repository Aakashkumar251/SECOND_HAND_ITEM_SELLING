package com.seconddeal.backend.service;

//package com.seconddeal.backend.wishlist;

import com.seconddeal.backend.model.Product;
import com.seconddeal.backend.model.Wishlist;
import com.seconddeal.backend.model.Product;
import com.seconddeal.backend.repository.ProductRepository;
import com.seconddeal.backend.dto.ProductResponse;
import com.seconddeal.backend.repository.ProductRepository;
import com.seconddeal.backend.repository.WishlistRepository;
import com.seconddeal.backend.model.User;
import com.seconddeal.backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepo userRepository;
    private final ProductRepository productRepository;

    public String toggleWishlist(String email, Long productId) {
        User user = userRepository.findByEmail(email);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // if already wishlisted → remove it
        if (wishlistRepository.existsByUserIdAndProductId(user.getId(), productId)) {
            Wishlist w = wishlistRepository
                    .findByUserIdAndProductId(user.getId(), productId).get();
            wishlistRepository.delete(w);
            return "Removed from wishlist";
        }

        // else add it
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlistRepository.save(wishlist);
        return "Added to wishlist";
    }

    public List<ProductResponse> getMyWishlist(String email) {
        User user = userRepository.findByEmail(email);
        return wishlistRepository.findByUserId(user.getId())
                .stream()
                .map(w -> toResponse(w.getProduct()))
                .collect(Collectors.toList());
    }

    private ProductResponse toResponse(Product product) {
        ProductResponse res = new ProductResponse();
        res.setId(product.getId());
        res.setTitle(product.getTitle());
        res.setPrice(product.getPrice());
        res.setLocation(product.getLocation());
        res.setCategory(product.getCategory().name());
        res.setStatus(product.getStatus());
        res.setImageUrls(product.getImageUrls());
        res.setSellerName(product.getSeller().getName());
        res.setSellerEmail(product.getSeller().getEmail());
        res.setSellerId(product.getSeller().getId());
        res.setCreatedAt(product.getCreatedAt());
        return res;
    }
}
