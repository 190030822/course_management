package com.explorecode.course_management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.explorecode.course_management.dto.CourseDto;
import com.explorecode.course_management.entity.Course;
import com.explorecode.course_management.exception.CourseNotFoundException;

@Service
public interface CourseService {

    public CourseDto addCourse(CourseDto courseDto);

    public CourseDto getCourseById(Long courseId) throws CourseNotFoundException;

    public CourseDto updateCourse(long courseId, CourseDto courseDto);

    public void deteteCourse(long courseId);

    public List<CourseDto> getAllCourses();

    public List<CourseDto> getCoursesByTitle(String title);

    public List<CourseDto> searchCoursesWithFilters(String title, String instructor, LocalDate startDate);

    public List<CourseDto> searchCourseByTitleAndStartDate(String title, String startDate);
}
