package com.jwtandsecurity.Service;

import com.jwtandsecurity.DTO.MemberDTO;
import com.jwtandsecurity.Token.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    public String login(MemberDTO memberDTO) {

        Long expired = 60 * 60 * 1000L;
        return JwtUtil.createJwt(memberDTO, expired);
    }

}
