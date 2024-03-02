package com.explorecode.course_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.explorecode.course_management.dto.CourseDto;
import com.explorecode.course_management.entity.Course;
import com.explorecode.course_management.exception.CourseNotFoundException;
import com.explorecode.course_management.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        return convertCourseToCourseDto(courseRepository.save(convertDtoToCourse(courseDto)));
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream().map((course) -> convertCourseToCourseDto(course)).collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        return convertCourseToCourseDto(courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with id " + courseId + "not found")));
    }

    @Override
    public CourseDto updateCourse(long courseId, CourseDto courseDto) {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with id " + courseId + "not found"));
        Course editedCourse = convertDtoToCourse(courseDto);
        if (Objects.nonNull(editedCourse.getTitle()) && !"".equalsIgnoreCase(editedCourse.getTitle())) {
            course.setTitle(editedCourse.getTitle());
        }
        if (Objects.nonNull(editedCourse.getDescription()) && !"".equalsIgnoreCase(editedCourse.getDescription())) {
            course.setDescription(editedCourse.getDescription());
        } 
        if (Objects.nonNull(editedCourse.getInstructor()) && !"".equalsIgnoreCase(editedCourse.getInstructor())) {
            course.setInstructor(editedCourse.getInstructor());
        }
        if (Objects.nonNull(editedCourse.getCourseDuration())) {
            course.setCourseDuration(editedCourse.getCourseDuration());
        }
        if (Objects.nonNull(editedCourse.getStartDate())) {
            course.setStartDate(editedCourse.getStartDate());
        }
        if (Objects.nonNull(editedCourse.getEndDate())) {
            course.setEndDate(editedCourse.getEndDate());
        }
        if (Objects.nonNull(editedCourse.getSyllabus()) && !"".equalsIgnoreCase(editedCourse.getSyllabus())) {
            course.setSyllabus(editedCourse.getSyllabus());
        }
        return convertCourseToCourseDto(courseRepository.save(course));
    }

    @Override
    public void deteteCourse(long courseId) {
        courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course Not Found with Id" + courseId));
        courseRepository.deleteById(courseId);
    }   

    @Override
    public List<CourseDto> getCoursesByTitle(String title) {
        return courseRepository.findByTitleStartingWith(title).stream().map((course) -> convertCourseToCourseDto(course)).collect(Collectors.toList());
    }

     @Override
    public List<CourseDto> searchCoursesWithFilters(String title, String instructor, LocalDate startDate) throws CourseNotFoundException {
        List<Course> filteredCourses = courseRepository.searchCoursesWithFilters(title, instructor, startDate);
        return filteredCourses.stream().map((course) -> convertCourseToCourseDto(course)).collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> searchCourseByTitleAndStartDate(String title, String startDate) {
        List<Course> filteredCourses = courseRepository.searchCourseByTitleAndStartDate(title, startDate);
        return filteredCourses.stream().map((course) -> convertCourseToCourseDto(course)).collect(Collectors.toList());
    }

    private Course convertDtoToCourse(CourseDto courseDto) {
        return modelMapper.map(courseDto, Course.class);
    }

    private CourseDto convertCourseToCourseDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }

    
}
