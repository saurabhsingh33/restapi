package com.restapidemo.restapi.service;

import com.restapidemo.restapi.domain.Course;
import com.restapidemo.restapi.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        return courseRepo.findAll();
        /*return courseList;*/
    }

    @Override
    public Course getCourse(int courseId) {

        return courseRepo.findById(courseId);
        /*Course course = null;
        for(Course c: courseList) {
            if (c.getId() == courseId) {
                course = c;
            }
        }*/

        //return course;
    }

    @Override
    public String addCourse(Course course) {
       /* courseList.add(course);
        return "Added course to database";*/

        courseRepo.save(course);
        return "Added course to database";
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
    public void deleteCourse(int id) {
        /*courseList = this.courseList.stream().filter(e -> e.getId() != id).collect(Collectors.toList());*/

        Course course = courseRepo.findById((int) id);
        courseRepo.delete(course);
    }
}
