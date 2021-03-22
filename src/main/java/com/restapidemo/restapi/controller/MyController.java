package com.restapidemo.restapi.controller;

import com.restapidemo.restapi.domain.Course;
import com.restapidemo.restapi.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class MyController {

    Logger logger = LoggerFactory.getLogger(MyController.class);
    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String getCourses(Model model) {
        model.addAttribute("listCourses", courseService.courses());
        logger.info("Return to home page");
        return "index";
    }

    @GetMapping("courses/{courseId}")
    public Course getCourse(@PathVariable String courseId) {
        Course course = this.courseService.getCourse(Long.parseLong(courseId));
        logger.info(ResponseEntity.status(HttpStatus.OK).build().toString());
        return course;
    }

    @PostMapping("courses")
    public void addCourse(@RequestBody Course course) {
        this.courseService.addCourse(course);
    }

    @GetMapping("addCourse")
    public String addNewCourse(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "NewCourse";
    }

    @PostMapping("saveCourse")
    public String saveCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
        if (result.hasErrors()) {
            logger.info(result.toString());
            return "NewCourse";
        }
        this.courseService.addCourse(course);
        return "redirect:/";
    }

    @GetMapping("updateCourse/{id}")
    public String updateCourseForm(@PathVariable (value = "id") String id, Model model) {
        Course course = courseService.getCourse(Long.parseLong(id));
        model.addAttribute("course", course);
        logger.info(ResponseEntity.status(HttpStatus.OK).build().toString());
        return "updateEmployee";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable (value = "id") Long id) {
        this.courseService.deleteCourse(id);
        return "redirect:/";
    }

    @PutMapping("courses")
    public Course updateCourse(@RequestBody Course course) {
        return this.courseService.updateCourse(course);
    }

    @DeleteMapping("courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
        try {
            this.courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
