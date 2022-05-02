package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.dto.request.RegisterRequest;
import com.ivarrace.gringotts.exception.UserAlreadyRegisteredException;
import com.ivarrace.gringotts.repository.UserRepository;
import com.ivarrace.gringotts.repository.model.users.Role;
import com.ivarrace.gringotts.repository.model.users.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository,
                       @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException(username);
    }

    public User register(RegisterRequest request){
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new UserAlreadyRegisteredException(request.getUsername());
        }
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        newUser.setAuthorities(Collections.singleton(Role.USER));
        return userRepository.save(newUser);
    }

}