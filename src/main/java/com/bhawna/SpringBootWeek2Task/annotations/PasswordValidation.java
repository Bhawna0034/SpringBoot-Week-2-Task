package com.bhawna.SpringBootWeek2Task.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {PasswordValidator.class})

public @interface PasswordValidation {
    String message() default "Password should contain at least one uppercase letter, one lowercase letter, one special character and have a minimum length of 10";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
