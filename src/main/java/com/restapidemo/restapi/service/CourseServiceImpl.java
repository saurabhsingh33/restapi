package com.restapidemo.restapi.service;

import com.restapidemo.restapi.domain.Course;
import com.restapidemo.restapi.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepo courseRepo;

    //List<Course> courseList;

    public CourseServiceImpl() {
        /*courseList = new ArrayList<>();
        courseList.add(new Course(200L, "Physics", "Concepts of Physics"));
        courseList.add(new Course(201L, "Java", "Basic Java"));*/
    }

    @Override
    public List<Course> courses() {
        List<Course> coursesFound = courseRepo.findAll();
        return coursesFound;
        /*return courseList;*/
    }

    @Override
    public Course getCourse(Long courseId) {

        Course course = null;
        try {
            Optional<Course> optional = courseRepo.findById(courseId);

            if (optional.isPresent()) {
                course = optional.get();
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return course;
        /*Course course = null;
        for(Course c: courseList) {
            if (c.getId() == courseId) {
                course = c;
            }
        }*/

        //return course;
    }

    @Override
    public void addCourse(Course course) {
       /* courseList.add(course);
        return "Added course to database";*/

        courseRepo.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        /*courseList.forEach(e -> {
            if (e.getId() == course.getId()) {
                e.setTitle(course.getTitle());
                e.setDescription(course.getDescription());
            }
        });
        return course;*/

        return courseRepo.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        /*courseList = this.courseList.stream().filter(e -> e.getId() != id).collect(Collectors.toList());*/

        Optional<Course> optional = courseRepo.findById(id);
        Course course = null;
        if (optional.isPresent()) {
            course = optional.get();
        } else {
            throw new RuntimeException(" Course not found for id ::" + id);
        }
        courseRepo.delete(course);
    }
}
