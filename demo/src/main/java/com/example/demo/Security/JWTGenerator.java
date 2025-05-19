package com.example.demo.Security;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
@Component
public class JWTGenerator {

    private static final long EXPIRATION_TIME = 7000000L; 
    private static final Key Key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Use a secure key in production


        public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + EXPIRATION_TIME); 
        
        // Generar the JWT token
        String token = Jwts.builder().setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(Key)
                .compact();
            
        return token;
    }

    public String extractUsernameString(String token) {
        return Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(token).getBody().getSubject();
    }

    /* */

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
