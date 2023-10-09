package com.plannerapp.service;

import com.plannerapp.model.entity.dto.AllAvailableTasksDTO;
import com.plannerapp.model.entity.dto.CurrentUserDTO;
import com.plannerapp.model.entity.dto.UserRegistrationDTO;
import com.plannerapp.model.entity.dto.UserTasksDTO;
import com.plannerapp.model.entity.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Set;

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

    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        String encodePassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        user.setPassword(encodePassword);
        this.userRepository.save(user);
        Optional<User> newUser = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
        return newUser.isPresent();
    }

    public Optional<User> findUserByUsername(String value) {
        return this.userRepository.findByUsername(value);
    }

    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
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

    public Optional<CurrentUserDTO> findCurrentUser(Long id) {
        return this.userRepository.findByUserId(id);
    }


}
