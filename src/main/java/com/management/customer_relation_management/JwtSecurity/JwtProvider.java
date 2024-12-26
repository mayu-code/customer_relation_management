package com.management.customer_relation_management.JwtSecurity;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    public static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRETE_KEY.getBytes());

    public static String generateJwtToken(Authentication auth){
            String jwt = Jwts.builder()
                        .setIssuer("ok").setIssuedAt(new Date())
                        .setExpiration(new Date(new Date().getTime()+84600000))
                        .claim("email", auth.getName())
                        .signWith(key)
                        .compact();
            return jwt;
    }

    public static String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser()
                        .setSigningKey(key).build()
                        .parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }


}
