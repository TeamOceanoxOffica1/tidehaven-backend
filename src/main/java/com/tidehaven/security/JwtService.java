package com.tidehaven.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtService {

    private static final String SECRET = "THIS_IS_A_SUPER_LONG_SECRET_KEY_CHANGE_ME_123456";

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(Long userId) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date())
                .signWith(key)
                .compact();
    }

    public static Long extractUserId(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .verifyWith((javax.crypto.SecretKey) key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject()
        );
    }
}