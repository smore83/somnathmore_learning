package com.sso.ssointegration.service.impl;


import com.sso.ssointegration.dto.AuthenticationRequest;
import com.sso.ssointegration.dto.AuthenticationResponse;
import com.sso.ssointegration.dto.RegisterRequest;
import com.sso.ssointegration.entity.Role;
import com.sso.ssointegration.entity.User;
import com.sso.ssointegration.exception.RecordAlreadyExistException;
import com.sso.ssointegration.exception.RecordNotFoundException;
import com.sso.ssointegration.repository.UserRepository;
import com.sso.ssointegration.service.JwtService;
import com.sso.ssointegration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public UserDetails register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new RecordAlreadyExistException("User with email already exists: " + request.getEmail());
        } else {
            var user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            return userDetailsService.loadUserByUsername(request.getEmail());
        }
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new RecordNotFoundException("user not found by email:" + request.getEmail()));

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}