package com.explorecode.course_management.custom_validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseInstructorConfineValidation.class)
public @interface CourseInstructorValidation {

    String message() default "Instructor should be of Rammaiah, pradeepini, srikanth";

    Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
