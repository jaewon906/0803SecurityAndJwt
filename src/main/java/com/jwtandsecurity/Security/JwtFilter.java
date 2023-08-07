package com.jwtandsecurity.Security;

import com.jwtandsecurity.Token.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username="Àç¿ø";
        String password="123423";

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);


        if(authorization==null || authorization.equals("")){
            log.error("No authorization");

            filterChain.doFilter(request,response);
        }

        else{
            boolean validateToken = jwtUtil.validateToken(authorization);
            if (validateToken){

                String map = modelMapper.map(MemberRole.USER, String.class);

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority(map)));
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
                filterChain.doFilter(request,response);
            }
        }
    }
}
