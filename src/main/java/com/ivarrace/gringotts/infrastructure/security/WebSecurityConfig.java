package com.ivarrace.gringotts.infrastructure.security;

import com.ivarrace.gringotts.infrastructure.security.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthService authService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(AuthService authService,
                             JwtAuthorizationFilter jwtAuthorizationFilter,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.authService = authService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set permissions on endpoints
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.GET, "/api-docs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated();

        // Add JWT token filter
        http.addFilterBefore(
                jwtAuthorizationFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}