package com.example.ShoppingList.service;

import com.example.ShoppingList.model.dto.UserRegistrationDTO;
import com.example.ShoppingList.model.entity.User;
import com.example.ShoppingList.repository.UserRepository;
import com.example.ShoppingList.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    private final HttpSession httpSession;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.httpSession = httpSession;
    }

    public Optional<User> findUserByUsername(String value) {
        return this.userRepository.findByUsername(value);
    }

    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        String encodePassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        user.setPassword(encodePassword);
        this.userRepository.save(user);
        Optional<User> newUser = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
        return newUser.isPresent();
    }

    public boolean checkCredentials(String username, String password) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        }
        return passwordEncoder.matches(password, user.get().getPassword());
    }

    public void login(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isPresent()) {
            this.loggedUser.setId(user.get().getId());
            this.loggedUser.setUsername(user.get().getUsername());
        }
    }

    public void logout() {
        this.httpSession.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }
}
