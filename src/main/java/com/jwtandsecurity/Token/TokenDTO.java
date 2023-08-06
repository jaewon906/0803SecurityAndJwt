package com.jwtandsecurity.Token;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;

}
