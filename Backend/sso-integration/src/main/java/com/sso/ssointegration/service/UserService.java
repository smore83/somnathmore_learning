package com.sso.ssointegration.service;

import com.sso.ssointegration.dto.AuthenticationRequest;
import com.sso.ssointegration.dto.AuthenticationResponse;
import com.sso.ssointegration.dto.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails register(RegisterRequest request);

    public AuthenticationResponse login(AuthenticationRequest request);
}
