package com.jwtandsecurity.Service;

import com.jwtandsecurity.DTO.MemberDTO;
import com.jwtandsecurity.Token.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    private Long expired = 60 * 1000L;

    public String login(MemberDTO memberDTO) {

        return JwtUtil.createJwt(memberDTO, expired);
    }

}
