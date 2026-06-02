package com.seconddeal.backend.service;

//package com.seconddeal.backend.product;

import com.seconddeal.backend.dto.ProductRequest;
import com.seconddeal.backend.dto.ProductResponse;
import com.seconddeal.backend.model.Category;
import com.seconddeal.backend.model.Product;
import com.seconddeal.backend.repository.ProductRepository;
import com.seconddeal.backend.repository.UserRepo;
import com.seconddeal.backend.model.User;
import com.seconddeal.backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepo userRepository;

    // Convert Product to ProductResponse
    private ProductResponse toResponse(Product product) {
        ProductResponse res = new ProductResponse();
        res.setId(product.getId());
        res.setTitle(product.getTitle());
        res.setDescription(product.getDescription());
        res.setPrice(product.getPrice());
        res.setLocation(product.getLocation());
        res.setCategory(product.getCategory().name());
        res.setStatus(product.getStatus());
        res.setImageUrls(product.getImageUrls());
        res.setCreatedAt(product.getCreatedAt());
        res.setSellerName(product.getSeller().getName());
        res.setSellerEmail(product.getSeller().getEmail());
        res.setSellerId(product.getSeller().getId());
        return res;
    }

    // Post a new product
    public ProductResponse createProduct(ProductRequest request, String sellerEmail) {
        User seller = userRepository.findByEmail(sellerEmail);

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setLocation(request.getLocation());
        product.setCategory(Category.valueOf(request.getCategory()));
        product.setImageUrls(request.getImageUrls());
        product.setSeller(seller);

        return toResponse(productRepository.save(product));
    }

    // Get all active products
    public List<ProductResponse> getAllProducts() {
        return productRepository.findByStatus("ACTIVE")
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Get single product by id
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        return toResponse(product);
    }

    // Get products by seller
    public List<ProductResponse> getMyProducts(String sellerEmail) {
        User seller = userRepository.findByEmail(sellerEmail);
        return productRepository.findBySellerId(seller.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Search products
    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Mark product as sold
    public ProductResponse markAsSold(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        product.setStatus("SOLD");
        return toResponse(productRepository.save(product));
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}