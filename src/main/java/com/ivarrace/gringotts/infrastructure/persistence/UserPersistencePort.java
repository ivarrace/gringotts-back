package com.ivarrace.gringotts.infrastructure.persistence;

import com.ivarrace.gringotts.infrastructure.security.dto.User;

import java.util.Optional;

public interface UserPersistencePort {

    Optional<User> findByUsername(String username);

    User save(User newUser);
}
