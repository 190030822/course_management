package com.explorecode.course_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.explorecode.course_management.entity.Course;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long>{

    public List<Course> findByTitleStartingWith(String title);

    @Transactional
    @Modifying
    @Query("Select c from Course c where ((:title is NULL OR c.title LIKE :title%) AND " +
    "(:instructor is NULL OR c.instructor = :instructor) AND (:startDate is NULL OR c.startDate = :startDate))")
    public List<Course> searchCoursesWithFilters(String title, String instructor, LocalDate startDate);


    @Transactional
    @Modifying
    @Query("select c from Course c where (:title is NULL OR c.title LIKE %:title " +
    "AND :startDate is NULL OR c.startDate = :startDate)")
    public List<Course> searchCourseByTitleAndStartDate(String title, String startDate);
}

