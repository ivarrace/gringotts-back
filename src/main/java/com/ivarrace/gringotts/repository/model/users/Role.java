package com.ivarrace.gringotts.repository.model.users;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}