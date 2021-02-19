package com.restapidemo.restapi.repository;

import com.restapidemo.restapi.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
    Course findById(int courseId);
}
