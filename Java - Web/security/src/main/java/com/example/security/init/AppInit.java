package com.example.security.init;

import com.example.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final UserService userService;

    public AppInit(UserService userService) {

        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        userService.init();
    }
}
