package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.UserEntity;
import com.ivarrace.gringotts.infrastructure.security.dto.Role;
import com.ivarrace.gringotts.infrastructure.security.dto.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static User entityToDomain(UserEntity entity){
        if(entity==null) return null;
        User domain = new User();
        domain.setId(entity.getId());
        domain.setCreatedDate(entity.getCreatedDate());
        domain.setLastModified(entity.getLastModified());
        domain.setUsername(entity.getUsername());
        domain.setPassword(entity.getPassword());
        domain.setAuthorities(authoritiesToDomain(entity.getAuthorities()));
        domain.setEnabled(entity.isEnabled());
        domain.setNonExpired(entity.isNonExpired());
        domain.setNonLocked(entity.isNonLocked());
        domain.setCredentialNonExpired(entity.isCredentialNonExpired());
        return domain;
    }

    public static UserEntity domainToEntity(User domain) {
        if(domain==null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setCreatedDate(domain.getCreatedDate());
        entity.setLastModified(domain.getLastModified());
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        entity.setAuthorities(authoritiesToEntity(domain.getAuthorities()));
        entity.setEnabled(domain.isEnabled());
        entity.setNonExpired(domain.isNonExpired());
        entity.setNonLocked(domain.isNonLocked());
        entity.setCredentialNonExpired(domain.isCredentialNonExpired());
        return entity;
    }

    private static Set<String> authoritiesToEntity(
            Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    private static Set<Role> authoritiesToDomain(
            Set<String> authorities) {
        return authorities.stream().map(auth -> new Role(auth)).collect(Collectors.toSet());
    }
}
