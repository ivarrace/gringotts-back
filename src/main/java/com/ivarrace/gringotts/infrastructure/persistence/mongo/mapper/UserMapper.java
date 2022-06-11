package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.User;
import com.ivarrace.gringotts.infrastructure.security.dto.RoleDto;
import com.ivarrace.gringotts.infrastructure.security.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserMapper {

    private UserMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static UserDto entityToDto(User entity){
        if(entity==null) return null;
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModified(entity.getLastModified());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setAuthorities(entityToDtoAuthorities(entity.getAuthorities()));
        dto.setEnabled(entity.isEnabled());
        dto.setNonExpired(entity.isNonExpired());
        dto.setNonLocked(entity.isNonLocked());
        dto.setCredentialNonExpired(entity.isCredentialNonExpired());
        return dto;
    }

    public static User dtoToEntity(UserDto dto) {
        if(dto==null) return null;
        User entity = new User();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModified(dto.getLastModified());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setAuthorities(dtoToEntityAuthorities(dto.getAuthorities()));
        entity.setEnabled(dto.isEnabled());
        entity.setNonExpired(dto.isNonExpired());
        entity.setNonLocked(dto.isNonLocked());
        entity.setCredentialNonExpired(dto.isCredentialNonExpired());
        return entity;
    }

    private static Set<String> dtoToEntityAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        Set<String> list = new HashSet<>();
        authorities.forEach(auth -> list.add(auth.getAuthority()));
        return list;
    }

    private static Set<RoleDto> entityToDtoAuthorities(
            Set<String> authorities) {
        Set<RoleDto> list = new HashSet<>();
        authorities.forEach(auth -> list.add(new RoleDto(auth)));
        return list;
    }
}
