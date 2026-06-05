package com.seconddeal.backend.model;

//package com.seconddeal.backend.wishlist;

//import com.seconddeal.backend.product.Product;
//import com.seconddeal.backend.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wishlists")
@Data
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
