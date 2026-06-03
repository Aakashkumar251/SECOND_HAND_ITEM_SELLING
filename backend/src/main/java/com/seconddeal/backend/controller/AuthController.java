package com.seconddeal.backend.controller;

import com.seconddeal.backend.dto.*;
import com.seconddeal.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController   //"This class handles HTTP requests and returns JSON data."
@RequestMapping("/api/auth") //
//@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:4200") //Allow requests coming from the frontend running at http://localhost:4200
//
public class AuthController {
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
