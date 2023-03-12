package com.javaevangelist.springapp.dao;

import com.javaevangelist.springapp.model.User;
import com.javaevangelist.springapp.web.dto.UserRegistrationDTO;

public interface UserDAO {
    User save(User user);

    User findByEmail(String username);
}
