package com.Javas17Challenge.controller;

import com.Javas17Challenge.entity.Course;
import com.Javas17Challenge.entity.CourseGpa;
import com.Javas17Challenge.entity.CourseResponse;
import com.Javas17Challenge.exception.CourseException;
import com.Javas17Challenge.validation.CourseValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private List<Course> courses;
    private CourseGpa lowCourseGpa;
    private CourseGpa mediumCourseGpa;
    private CourseGpa highCourseGpa;

    @Autowired
    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowCourseGpa, @Qualifier("mediumCourseGpa") CourseGpa mediumCourseGpa, @Qualifier("highCourseGpa") CourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
    }

    @PostConstruct
    public void init() {
        courses = new ArrayList<>();
    }

    @GetMapping
    public List<Course> get() {
        return this.courses;
    }

    @GetMapping("/{name}")
    public Course get(@PathVariable String name) {

        CourseValidation.name(name);

        return courses.stream()
                .filter(course -> course.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new CourseException(String.format("%s cannot be found", name), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> post(@RequestBody Course course) {
        CourseValidation.name(course.getName());
        CourseValidation.uniqueName(course, courses);
        CourseValidation.credit(course);
        CourseValidation.idExists(courses, course.getId());

        courses.add(course);
        System.out.println(courses);

        return new ResponseEntity<>(new CourseResponse(course, totalGpa(course)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> put(@PathVariable long id, @RequestBody Course course) {
        CourseValidation.name(course.getName());
        CourseValidation.credit(course);

        CourseValidation.idDoesNotExist(courses, id);

        course.setId(id);
        courses.add(course);
        CourseResponse resp = new CourseResponse(course, totalGpa(course));
        System.out.println(resp);

        ResponseEntity<CourseResponse> responseEntity = new ResponseEntity<>(resp, HttpStatus.OK);

        System.out.println(responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {

        Course foundCourse = (Course)courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CourseException(String.format("Course with id: %d does not exist", id), HttpStatus.BAD_REQUEST));

        courses.remove(foundCourse);
    }

    private int totalGpa(Course course) {

        if( course.getCredit() <=2 ) {
            return course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();
        }
        if ( course.getCredit() == 3 ) {
            return course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();
        }

        return course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
    }
}
