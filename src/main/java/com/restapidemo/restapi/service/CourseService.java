package com.restapidemo.restapi.service;

import com.restapidemo.restapi.domain.Course;

import java.util.List;

public interface CourseService {

    public List<Course> courses();

    public Course getCourse(int courseId);

    public String addCourse(Course course);

    public Course updateCourse(Course course);

    public void deleteCourse(int id);
}
