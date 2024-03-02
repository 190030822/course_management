package com.explorecode.course_management.custom_validator;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseInstructorConfineValidation implements ConstraintValidator<CourseInstructorValidation, String>{

    @Override
    public boolean isValid(String instructor, ConstraintValidatorContext arg1) {
        return instructors.contains(instructor);
    }

    List<String> instructors = Arrays.asList("Rammaiah", "pradeepini", "srikanth");

}
