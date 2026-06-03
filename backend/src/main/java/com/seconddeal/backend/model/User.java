package com.seconddeal.backend.model;



// model means tables or entity in database it is totally different from DTO

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Data  // lombok
public class User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private String role = "USER"; // USER or ADMIN

    private LocalDateTime createdAt = LocalDateTime.now();
    private String profileImage =  "https://res.cloudinary.com/dey3hyep9/image/upload/v1780465119/seconddeal/dcnjlr6guc2fw00puxfc.png";

}
