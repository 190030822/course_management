package com.explorecode.course_management.custom_validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EndDateAfterStartDateValidation.class)
public @interface EndDateAfterStartDate {
    
    String message() default "End Date should be after start date";

    Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
