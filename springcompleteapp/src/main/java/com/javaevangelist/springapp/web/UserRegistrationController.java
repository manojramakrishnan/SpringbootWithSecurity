package com.javaevangelist.springapp.web;

import com.javaevangelist.springapp.service.UserService;
import com.javaevangelist.springapp.web.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {



    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String register(@ModelAttribute("user") UserRegistrationDTO registrationDTO){

    userService.save(registrationDTO);
    return "redirect:/registration?success";
    }
}
