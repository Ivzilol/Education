package com.example.security.web;

import com.example.security.model.dto.UserRegisterDTO;
import com.example.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(UserRegisterDTO userRegisterDTO) {

        userService.registerAndLogin(userRegisterDTO);
        return "redirect:/";
    }
}
