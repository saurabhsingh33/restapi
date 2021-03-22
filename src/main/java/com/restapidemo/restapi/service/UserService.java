package com.restapidemo.restapi.service;

import com.restapidemo.restapi.domain.User;
import com.restapidemo.restapi.web.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
}
