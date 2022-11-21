package com.example.mobile.service;


import com.example.mobile.model.dto.UserRegisterDTO;
import com.example.mobile.model.entity.UserEntity;
import com.example.mobile.model.mapper.UserMapperImpl;
import com.example.mobile.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapperImpl userMapperImpl;
    private final UserDetailsService userDetailsService;
    private final EmailService emailService;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapperImpl userMapperImpl,
                       UserDetailsService userDetailsService,
                       EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapperImpl = userMapperImpl;
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO,
                                 Locale preferredLocale) {

        UserEntity newUser = userMapperImpl.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));


        this.userRepository.save(newUser);
        login(newUser.getEmail());
        emailService.sendRegistrationEmail(newUser.getEmail(),
                newUser.getFirstName() + " " + newUser.getLastName(),
                preferredLocale);
    }

    public void login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
    }

    public void createUserIfNotExist(String email) {
        var userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isEmpty()){
            var newUser = new UserEntity()
                    .setEmail(email)
                    .setPassword(null)
                    .setFirstName("New")
                    .setLastName("User")
                    .setUserRoles(List.of());
            userRepository.save(newUser);
        }
    }
}
