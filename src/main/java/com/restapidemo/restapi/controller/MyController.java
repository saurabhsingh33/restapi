package com.restapidemo.restapi.controller;

import com.restapidemo.restapi.domain.Course;
import com.restapidemo.restapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/")
    public String getCourses(Model model) {
        model.addAttribute("listCourses", courseService.courses());
        return "index.html";
    }

    @RequestMapping(value = "courses/{courseId}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable String courseId) {
        return this.courseService.getCourse(Long.parseLong(courseId));
    }

    @RequestMapping(value = "courses", method = RequestMethod.POST)
    public void addCourse(@RequestBody Course course) {
        this.courseService.addCourse(course);
    }

    @RequestMapping("addCourse")
    public String addNewCourse(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "NewCourse";
    }

    @RequestMapping(value = "saveCourse", method = RequestMethod.POST)
    public String saveCourse(@ModelAttribute("course") Course course) {
        this.courseService.addCourse(course);
        return "redirect:/";
    }

    @RequestMapping("updateCourse/{id}")
    public String updateCourseForm(@PathVariable (value = "id") String id, Model model) {
        Course course = courseService.getCourse(Long.parseLong(id));
        model.addAttribute("course", course);
        return "updateEmployee";
    }

    @RequestMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable (value = "id") Long id) {
        this.courseService.deleteCourse(id);
        return "redirect:/";
    }

    @RequestMapping(value = "courses", method = RequestMethod.PUT)
    public Course updateCourse(@RequestBody Course course) {
        return this.courseService.updateCourse(course);
    }

    @RequestMapping(value = "courses/{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
        try {
            this.courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
