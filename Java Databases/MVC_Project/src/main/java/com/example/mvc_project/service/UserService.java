package com.example.mvc_project.service;

import com.example.mvc_project.models.User;
import com.example.mvc_project.models.dto.RegistrationDTO;
import com.example.mvc_project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("default") ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void register(RegistrationDTO dto){
        User user = this.modelMapper.map(dto, User.class);

        this.userRepository.save(user);
    }
}
