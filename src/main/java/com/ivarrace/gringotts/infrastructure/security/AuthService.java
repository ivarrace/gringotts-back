package com.ivarrace.gringotts.infrastructure.security;

import com.ivarrace.gringotts.application.rest.dto.request.RegisterRequest;
import com.ivarrace.gringotts.domain.exception.UserAlreadyRegisteredException;
import com.ivarrace.gringotts.infrastructure.persistence.UserPersistencePort;
import com.ivarrace.gringotts.infrastructure.security.dto.Role;
import com.ivarrace.gringotts.infrastructure.security.dto.User;
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
        Optional<User> user = userPersistencePort.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException(username);
    }

    public User register(RegisterRequest request){
        Optional<User> user = userPersistencePort.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new UserAlreadyRegisteredException(request.getUsername());
        }
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        newUser.setAuthorities(Collections.singleton(Role.USER));
        return userPersistencePort.save(newUser);
    }

}