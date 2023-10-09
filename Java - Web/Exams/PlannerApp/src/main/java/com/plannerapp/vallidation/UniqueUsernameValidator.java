package com.plannerapp.vallidation;


import com.plannerapp.service.UserService;
import com.plannerapp.vallidation.annotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String > {

    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.findUserByUsername(value).isEmpty();
    }
}
