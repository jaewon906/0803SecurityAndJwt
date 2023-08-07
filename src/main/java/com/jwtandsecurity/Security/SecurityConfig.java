package com.jwtandsecurity.Security;

import com.jwtandsecurity.Service.MemberService;
import com.jwtandsecurity.Token.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> {
                    csrf.disable();
                })
                .cors(cors -> cors.disable())
                .formLogin(form -> form.disable())
                .authorizeHttpRequests(req -> {
                    req
                            .requestMatchers("/api/hello").permitAll()
                            .requestMatchers("/api/bye").authenticated()
                            .requestMatchers("/api/authenticated").authenticated()
                            .requestMatchers("api/role").hasRole("USER");
                })
                .addFilterBefore(new JwtFilter(new ModelMapper(),jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
