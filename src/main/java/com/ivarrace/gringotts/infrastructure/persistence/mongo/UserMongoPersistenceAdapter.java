package com.ivarrace.gringotts.infrastructure.persistence.mongo;

import com.ivarrace.gringotts.infrastructure.persistence.UserPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.User;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.UserRepository;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper.UserMapper;
import com.ivarrace.gringotts.infrastructure.security.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserMongoPersistenceAdapter implements UserPersistencePort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return Optional.of(UserMapper.entityToDto(user.get()));
        }
        return Optional.empty();
    }

    @Override
    public UserDto save(UserDto newUser) {
        User user = userRepository.save(UserMapper.dtoToEntity(newUser));
        return UserMapper.entityToDto(user);
    }
}
