package com.jwtandsecurity.Controller;

import com.jwtandsecurity.DTO.MemberDTO;
import com.jwtandsecurity.Service.MemberService;
import com.jwtandsecurity.Token.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class Controller {

    private final MemberService memberService;

    @PostMapping("hello")
    public TokenDTO hello(MemberDTO memberDTO) {
        return memberService.login(memberDTO);
    }

    @PostMapping("authenticated")
    public String authenticated() {
        return "ok";
    }

}
