package com.explorecode.course_management.dto;

import java.time.LocalDate;

import com.explorecode.course_management.custom_validator.CourseInstructorValidation;
import com.explorecode.course_management.custom_validator.EndDateAfterStartDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EndDateAfterStartDate
public class CourseDto {

    private Long courseId;
    @NotNull(message = "description field shouldn't be null")
    @Size(max = 100, min = 1, message = "title feild length should be in range of (1 to 100) characters")
    private String title;
    @NotNull(message = "description field shouldn't be null")
    private String description;
    @NotNull(message = "instructor field shouldn't be null")
    @CourseInstructorValidation
    private String instructor;
    @NotNull(message = "courseDuration field shouldn't be null")
    private int courseDuration;
    @NotNull(message = "startDate field shouldn't be null")
    @Future(message = "start date should be in future")
    private LocalDate startDate;
    @Future(message = "end date should be in future")
    private LocalDate endDate;
    private String syllabus;

}