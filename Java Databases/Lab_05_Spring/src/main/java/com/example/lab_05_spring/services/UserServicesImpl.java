package com.example.lab_05_spring.services;

import com.example.lab_05_spring.models.User;
import com.example.lab_05_spring.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        User found = userRepository
                .findByUsername(user.getUsername());

        if (found == null) {
            this.userRepository.save(user);
        }


    }
}
