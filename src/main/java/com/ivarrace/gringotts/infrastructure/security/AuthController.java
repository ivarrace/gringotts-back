package com.ivarrace.gringotts.infrastructure.security;

import com.ivarrace.gringotts.infrastructure.security.dto.User;
import com.ivarrace.gringotts.infrastructure.security.jwt.JwtTokenUtil;
import com.ivarrace.gringotts.application.rest.dto.request.AuthRequest;
import com.ivarrace.gringotts.application.rest.dto.request.RegisterRequest;
import com.ivarrace.gringotts.application.rest.dto.response.AuthResponse;
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
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authService = authService;
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

    @PostMapping("/register")
    public AuthResponse auth(@RequestBody @Valid RegisterRequest request) {
        User newUser = authService.register(request);
        AuthResponse response = new AuthResponse();
        String token = jwtTokenUtil.generateAccessToken(newUser);
        response.setAccessToken("Bearer "+token);
        response.setExpiresAt(jwtTokenUtil.getExpirationDate(token));
        return response;
    }
}
