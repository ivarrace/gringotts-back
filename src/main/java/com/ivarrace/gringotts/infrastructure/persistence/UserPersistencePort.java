package com.ivarrace.gringotts.infrastructure.persistence;

import com.ivarrace.gringotts.infrastructure.security.dto.UserDto;

import java.util.Optional;

public interface UserPersistencePort {

    Optional<UserDto> findByUsername(String username);

    UserDto save(UserDto newUser);
}
