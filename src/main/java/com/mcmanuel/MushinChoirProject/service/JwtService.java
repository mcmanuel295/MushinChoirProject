package com.mcmanuel.MushinChoirProject.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secretKeyString;

    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("Hmac SHA-256");
        SecretKey secretKey =  keyGen.generateKey();
        secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    private SecretKey getKey(){
        byte[] bytes = Base64.getDecoder().decode(secretKeyString);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(String email) {
        Map<String, Objects> claims = new HashMap<>();
        return Jwts
                .builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date( System.currentTimeMillis()+ (60 * 60 * 1000)))
                .signWith(getKey())
                .compact();
    }


    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validate(String token, UserDetails userDetails) {
        return !isExpired(token) && extractUsername(token).equals(userDetails.getUsername());
    }

    private boolean isExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date(System.currentTimeMillis() ));

    }
}
