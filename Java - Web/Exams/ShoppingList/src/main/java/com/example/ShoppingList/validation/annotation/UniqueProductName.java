package com.example.ShoppingList.validation.annotation;

import com.example.ShoppingList.validation.UniqueProductNameValidator;
import com.example.ShoppingList.validation.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueProductNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProductName {

    String message() default "Product already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
