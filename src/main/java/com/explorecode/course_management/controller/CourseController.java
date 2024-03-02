package com.explorecode.course_management.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.explorecode.course_management.dto.CourseDto;
import com.explorecode.course_management.exception.CourseNotFoundException;
import com.explorecode.course_management.service.CourseService;

import jakarta.validation.Valid;




@CrossOrigin(origins = "*")
@RestController
public class CourseController {
    
    @Autowired
    CourseService courseService;
    
    @PostMapping("/addCourse")
    public ResponseEntity<CourseDto> addCourse(@RequestBody @Valid CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseDto));
    }

    @GetMapping("/getCourseById/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("courseId") Long courseId) throws CourseNotFoundException{
            CourseDto course = courseService.getCourseById(courseId);
            return ResponseEntity.status(HttpStatus.OK).body(course);
       
    }

   @PutMapping("/updateCourse/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable("courseId") Long courseId, @RequestBody @Valid CourseDto courseDto) throws CourseNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(courseId, courseDto));
    }

    @GetMapping("getAllCourses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @DeleteMapping("deleteCourseById/{courseId}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable("courseId") Long courseId) {

        courseService.deteteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getCoursesByTitle/{title}")
    public ResponseEntity<List<CourseDto>> getCoursesByTitle(@PathVariable("title") String title) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCoursesByTitle(title));
    }

    @GetMapping("searchCourseByFilter")
    public ResponseEntity<List<CourseDto>> filterCourses(@RequestParam(name = "title", required = false) String title, @RequestParam(name = "instructor", required = false) String instructor, 
        @RequestParam(name = "startDate", required = false) LocalDate startDate) {
        System.out.println("hi nana "+startDate + "  " + instructor + " " + title);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.searchCoursesWithFilters(title, instructor, startDate));
    }
    
}
