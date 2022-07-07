package com.ivarrace.gringotts.infrastructure.security.dto;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    public static final Role USER = new Role("USER");
    public static final Role ADMIN = new Role("ADMIN");

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
