package com.ivarrace.gringotts.application.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authentication buildAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(
               this.username, this.password
        );
    }
}
