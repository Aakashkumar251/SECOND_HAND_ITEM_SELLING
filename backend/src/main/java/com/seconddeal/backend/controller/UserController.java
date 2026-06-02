package com.seconddeal.backend.controller;

import com.seconddeal.backend.dto.AuthResponse;
import com.seconddeal.backend.dto.LoginUser;
import com.seconddeal.backend.dto.RegisterUser;
import com.seconddeal.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController   //"This class handles HTTP requests and returns JSON data."
@RequestMapping("/api/auth") //
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:4200") //Allow requests coming from the frontend running at http://localhost:4200
//
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUser user){
        return ResponseEntity.ok(userService.register(user));
    }


    // ReqeustBody RegisterUser user------->>>>Spring takes the JSON: andd convert it into tht java object


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginUser user){
        return ResponseEntity.ok(userService.Login(user));
    }
}
