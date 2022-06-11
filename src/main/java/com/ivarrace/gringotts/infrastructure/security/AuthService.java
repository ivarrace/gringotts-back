package com.ivarrace.gringotts.infrastructure.security;

import com.ivarrace.gringotts.application.dto.request.RegisterRequest;
import com.ivarrace.gringotts.domain.exception.UserAlreadyRegisteredException;
import com.ivarrace.gringotts.infrastructure.persistence.UserPersistencePort;
import com.ivarrace.gringotts.infrastructure.security.dto.RoleDto;
import com.ivarrace.gringotts.infrastructure.security.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService  implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserPersistencePort userPersistencePort;

    public AuthService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserPersistencePort userPersistencePort) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDto> user = userPersistencePort.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException(username);
    }

    public UserDto register(RegisterRequest request){
        Optional<UserDto> user = userPersistencePort.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new UserAlreadyRegisteredException(request.getUsername());
        }
        UserDto newUser = new UserDto();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        newUser.setAuthorities(Collections.singleton(RoleDto.USER));
        return userPersistencePort.save(newUser);
    }

}