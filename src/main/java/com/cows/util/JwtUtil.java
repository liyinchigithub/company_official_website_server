package com.cows.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {
    // 密钥
    private static final String SECRET_KEY = "your_secret_key";
    // 生成token
    public static String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1)) // 1 hours
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }
    // 提取claims
    public static Claims extractClaims(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
    }
    // 提取用户名
    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    // 是否过期
    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}