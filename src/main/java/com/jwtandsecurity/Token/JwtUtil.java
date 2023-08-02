package com.jwtandsecurity.Token;

import com.jwtandsecurity.DTO.MemberDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String secretKey = "salgbTJWujnsJejsfSLfghaSSjAUWQdjGFUCDOWdJFUAIOFGDGAsdldfojwahfdgFGsajhsdl";
    protected static final byte[] secretKeyToByte = secretKey.getBytes();

    public static String createJwt(MemberDTO memberDTO, Long expiredMs){
        Claims claims = Jwts.claims();
        claims.put("username", memberDTO.getUsername());
        Key key = Keys.hmacShaKeyFor(JwtUtil.secretKeyToByte);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();

    }
}
