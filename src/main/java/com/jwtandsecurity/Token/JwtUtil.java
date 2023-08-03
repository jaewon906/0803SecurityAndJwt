package com.jwtandsecurity.Token;

import com.jwtandsecurity.DTO.MemberDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
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

    public boolean validateToken(String token) { //토큰 검증
        Key key = Keys.hmacShaKeyFor(JwtUtil.secretKeyToByte);
        try {
            //서블릿 요청으로부터 가져온 accessToken에 있는 인증키와 해당 인증키와 비교해서 검증하는 과정
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        } catch (Exception e) {
            log.info("Exception", e);
        }
        return false;
    }
}
