package com.likebookapp.validation.annotation;

import com.likebookapp.validation.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "Email already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
