package com.example.mvc_project.controller;

import com.example.mvc_project.models.dto.RegistrationDTO;
import com.example.mvc_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/register")
    public String registerView(RegistrationDTO registrationDTO){
        return "user/register";

    }

    @PostMapping(value = "user/register")
    public String doRegister(@Valid RegistrationDTO registrationDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "user/register";
        }

        this.userService.register(registrationDTO);
        return "user/login";
    }
}
