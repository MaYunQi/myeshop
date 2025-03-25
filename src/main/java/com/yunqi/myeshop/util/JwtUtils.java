package com.yunqi.myeshop.util;

import com.yunqi.myeshop.entity.userdto.AccountJwtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(AccountJwtDTO accountJwtDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Account_id", accountJwtDTO.getAccount_id());
        return buildToken(claims,accountJwtDTO);
    }
    private String buildToken(Map<String, Object> claims,AccountJwtDTO accountJwtDTO) {
        return Jwts.builder()
                .claims(claims)
                .subject(accountJwtDTO.getUsername())
                .claim("role", accountJwtDTO.getRole())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSigningKey(),Jwts.SIG.HS256)
                .compact();
    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public boolean validateToken(String token) {
        try{
            Claims claims=extractAllClaims(token);
            return isTokenExpired(claims);
        }
        catch (Exception e) {
            return false;
        }
    }
    public String getUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
