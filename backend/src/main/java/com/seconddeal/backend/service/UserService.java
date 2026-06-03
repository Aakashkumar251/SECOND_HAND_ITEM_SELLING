package com.seconddeal.backend.service;

import com.seconddeal.backend.dto.*;
import com.seconddeal.backend.model.User;
import com.seconddeal.backend.repository.UserRepo;
import com.seconddeal.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtservice;

    public AuthResponse register(RegisterUser request){
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // encrypt password
        user.setPhone(request.getPhone());

        userRepo.save(user);


        String token = jwtservice.generateToken(user.getEmail());

        return new AuthResponse(token, user.getName(), user.getEmail(), user.getRole());
    }




    public AuthResponse Login(LoginUser request){
        User user = userRepo.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password!");
        }
        String token = jwtservice.generateToken(user.getEmail());
        return new AuthResponse(token, user.getName(), user.getEmail(), user.getRole());
    }



    // Get my profile
    public ProfileResponse getProfile(String email) {
        User user = userRepo.findByEmail(email);
        return toProfileResponse(user);
    }

    // Update my profile
    public ProfileResponse updateProfile(String email, UpdateProfileRequest request) {
        User user = userRepo.findByEmail(email);

        if (request.getName() != null) user.setName(request.getName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getProfileImage() != null) user.setProfileImage(request.getProfileImage());

        userRepo.save(user);
        return toProfileResponse(user);
    }

    // Convert User to ProfileResponse
    private ProfileResponse toProfileResponse(User user) {
        ProfileResponse res = new ProfileResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setPhone(user.getPhone());
        res.setRole(user.getRole());
        res.setProfileImage(user.getProfileImage());
        res.setCreatedAt(user.getCreatedAt());
        return res;
    }



}
