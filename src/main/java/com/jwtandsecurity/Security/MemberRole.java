package com.jwtandsecurity.Security;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String roleName;

    MemberRole(String roleName) {
        this.roleName = roleName;
    }
}