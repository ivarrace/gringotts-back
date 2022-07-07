package com.ivarrace.gringotts.infrastructure.security.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto implements UserDetails {

    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
    private String username;
    private String password;
    private Set<RoleDto> authorities = new HashSet<>();
    private boolean enabled = true;
    private boolean nonExpired = true;
    private boolean nonLocked = true;
    private boolean credentialNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.nonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.nonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
