package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.config.JwtTokenUtil;
import com.ivarrace.gringotts.dto.request.AuthRequest;
import com.ivarrace.gringotts.dto.response.AuthResponse;
import com.ivarrace.gringotts.repository.model.users.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public AuthResponse auth(@RequestBody @Valid AuthRequest request) {
        Authentication authenticate = authenticationManager
                .authenticate(request.buildAuthenticationToken());
        User user = (User) authenticate.getPrincipal();
        AuthResponse response = new AuthResponse();
        String token = jwtTokenUtil.generateAccessToken(user);
        response.setAccessToken("Bearer "+token);
        response.setExpiresAt(jwtTokenUtil.getExpirationDate(token));
        return response;
    }

}
