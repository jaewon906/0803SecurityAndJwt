package com.jwtandsecurity.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private String username;
    private String password;
    private String role;
}
