package com.likebookapp.service;

import com.likebookapp.model.entity.dto.UserDTO;
import com.likebookapp.model.entity.dto.UserRegistrationDTO;
import com.likebookapp.model.entity.entity.User;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
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
        return userRepository.findByUsername(value);
    }

    public UserDTO findUserByEmail(String value) {
        User user = this.userRepository.findByEmail(value);
        if (user == null) {
            return null;
        }
        return this.mapUserDTO(user);
    }

    private UserDTO mapUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setEmail(user.getEmail());
    }

    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        String encodePassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        user.setPassword(encodePassword);
        this.userRepository.save(user);
        User newUser = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
        return newUser != null;
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

    public Optional<User> findCurrentUser(Long id) {
        return this.userRepository.findById(id);
    }
}
