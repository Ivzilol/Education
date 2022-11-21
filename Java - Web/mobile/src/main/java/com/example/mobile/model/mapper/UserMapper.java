package com.example.mobile.model.mapper;

import com.example.mobile.model.dto.UserRegisterDTO;
import com.example.mobile.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {


    UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);
}
