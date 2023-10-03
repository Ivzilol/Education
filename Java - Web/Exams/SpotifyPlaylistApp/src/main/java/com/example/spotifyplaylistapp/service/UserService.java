package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.dto.UserRegisterDTO;
import com.example.spotifyplaylistapp.model.entity.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
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

    public Optional<User> findUserByEmail(String value) {
        return this.userRepository.findByEmail(value);
    }

    public boolean registerUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        this.userRepository.save(user);
        Optional<User> newUser = this.userRepository.findByEmail(userRegisterDTO.getEmail());
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
        if(user.isPresent()) {
            loggedUser.setId(user.get().getId());
            loggedUser.setUsername(user.get().getUsername());
        }
    }

    public void logout() {
        this.httpSession.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }
}
