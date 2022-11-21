package com.example.mobile.model.mapper;

import com.example.mobile.model.dto.UserRegisterDTO;
import com.example.mobile.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO) {
        if (registerDTO == null){
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setPassword(registerDTO.getPassword());
        userEntity.setFirstName(registerDTO.getFirstName());
        userEntity.setLastName(registerDTO.getLastName());

        userEntity.setActive(true);

        return userEntity;
    }
}
