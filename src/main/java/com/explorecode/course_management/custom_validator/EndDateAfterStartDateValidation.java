package com.explorecode.course_management.custom_validator;

import java.util.Objects;

import com.explorecode.course_management.dto.CourseDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EndDateAfterStartDateValidation implements ConstraintValidator<EndDateAfterStartDate, CourseDto>{

    @Override
    public boolean isValid(CourseDto courseDto, ConstraintValidatorContext arg1) {
        return (Objects.nonNull(courseDto.getStartDate()) && Objects.nonNull(courseDto.getEndDate()) && courseDto.getStartDate().isBefore(courseDto.getEndDate()));
    }
    
}
