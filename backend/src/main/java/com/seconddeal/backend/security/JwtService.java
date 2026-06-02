package com.seconddeal.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final String secret= "seconddeal_super_secret_key_123456789_abcdef";

//This method is used in JWT authentication to create the secret key
// that will be used to sign and verify JWT tokens.
                private Key getKey(){
                    return Keys.hmacShaKeyFor(secret.getBytes());
                }

      // key is used for signature of token so that browser can see that the server is legit aand
      // browser can trust the server



    // Create token when user logs in
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
