package com.restapidemo.restapi.controller;

import com.restapidemo.restapi.domain.Course;
import com.restapidemo.restapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/")
    public String home() {
        return "Hello home page";
    }

    @RequestMapping(value = "courses", method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getCourses() {
        return this.courseService.courses();
    }

    @RequestMapping(value = "courses/{courseId}", method = RequestMethod.GET)
    @ResponseBody
    public Course getCourse(@PathVariable String courseId) {
        return this.courseService.getCourse(Integer.parseInt(courseId));
    }

    @RequestMapping(value = "courses", method = RequestMethod.POST)
    @ResponseBody
    public String addCourse(Course course) {
        return this.courseService.addCourse(course);
    }

    @RequestMapping(value = "courses", method = RequestMethod.PUT)
    @ResponseBody
    public Course updateCourse(@RequestBody Course course) {
        return this.courseService.updateCourse(course);
    }

    @RequestMapping(value = "courses/{courseId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
        try {
            this.courseService.deleteCourse(Integer.parseInt(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
