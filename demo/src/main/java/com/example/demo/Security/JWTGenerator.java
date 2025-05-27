package com.example.demo.Security;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

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
    Date expireDate = new Date(currentDate.getTime() + EXPIRATION_TIME);

    // Extraer roles del usuario autenticado
    String roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

    // Incluir roles como claim en el token
    return Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)
            .setIssuedAt(currentDate)
            .setExpiration(expireDate)
            .signWith(Key, SignatureAlgorithm.HS512)
            .compact();
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
