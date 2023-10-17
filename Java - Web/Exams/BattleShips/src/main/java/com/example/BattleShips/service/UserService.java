package com.example.BattleShips.service;

import com.example.BattleShips.model.dto.UserRegistrationDTO;
import com.example.BattleShips.model.entity.User;
import com.example.BattleShips.repository.UserRepository;
import com.example.BattleShips.util.LoggedUser;
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

    public UserService(UserRepository userRepository, PasswordEncoder encoder, LoggedUser loggedUser, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
        this.loggedUser = loggedUser;
        this.httpSession = httpSession;
    }

    public Optional<User> findUserByUsername(String value) {
        return this.userRepository.findByUsername(value);
    }

    public Optional<User> findUserByEmail(String value) {
        return this.userRepository.findByEmail(value);
    }

    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setFullName(userRegistrationDTO.getFullName());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
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
