package com.example.lab_05_spring;

import com.example.lab_05_spring.models.User;
import com.example.lab_05_spring.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private UserServices userServices;

    @Override
    public void run(String... args) throws Exception {

        User first = new User("ivo", 42);
        userServices.registerUser(first);

        User second = new User("ivo", 42);
        userServices.registerUser(second);

    }
}
