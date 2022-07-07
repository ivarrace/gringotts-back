package com.ivarrace.gringotts.infrastructure.persistence.mongo;

import com.ivarrace.gringotts.infrastructure.persistence.UserPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.UserEntity;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.UserRepository;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper.UserMapper;
import com.ivarrace.gringotts.infrastructure.security.dto.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserMongoPersistenceAdapter implements UserPersistencePort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return Optional.of(UserMapper.entityToDomain(user.get()));
        }
        return Optional.empty();
    }

    @Override
    public User save(User newUser) {
        UserEntity user = userRepository.save(UserMapper.domainToEntity(newUser));
        return UserMapper.entityToDomain(user);
    }
}
