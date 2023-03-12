package com.javaevangelist.springapp.dao;

import com.javaevangelist.springapp.model.User;
import com.javaevangelist.springapp.repository.UserRepository;
import com.javaevangelist.springapp.web.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }


}
