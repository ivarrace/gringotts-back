package com.ivarrace.gringotts.infrastructure.security.dto;

import org.springframework.security.core.GrantedAuthority;

public class RoleDto implements GrantedAuthority {

    public static final RoleDto USER = new RoleDto("USER");
    public static final RoleDto ADMIN = new RoleDto("ADMIN");

    private String authority;

    public RoleDto(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
