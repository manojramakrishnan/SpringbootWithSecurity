package com.javaevangelist.springapp.service;

import com.javaevangelist.springapp.model.User;
import com.javaevangelist.springapp.web.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO registrationDTO);
}
