package com.javaevangelist.springapp.service;

import com.javaevangelist.springapp.dao.UserDAO;
import com.javaevangelist.springapp.model.Role;
import com.javaevangelist.springapp.model.User;
import com.javaevangelist.springapp.web.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Override
    public User save(UserRegistrationDTO registrationDTO) {
        User user = new User(registrationDTO.getFirstName(),
                registrationDTO.getLastName(), registrationDTO.getEmail(),
                encoder.encode(registrationDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")));
        System.out.println(registrationDTO.getFirstName());
        System.out.println(registrationDTO.getLastName());
        System.out.println(registrationDTO.getEmail());
        System.out.println(registrationDTO.getPassword());
       return userDAO.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authoritiesRoleMap(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> authoritiesRoleMap(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
