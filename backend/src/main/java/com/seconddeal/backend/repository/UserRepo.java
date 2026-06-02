package com.seconddeal.backend.repository;

import com.seconddeal.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User ,  Long > {


    //   JpaRepository<EntityType, PrimaryKeyType>

    User findByEmail(String email);
    // User user = userRepository.findByEmail("a@gmail.com");

    boolean existsByEmail(String email);
}
