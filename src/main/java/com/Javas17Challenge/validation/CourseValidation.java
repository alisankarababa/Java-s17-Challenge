package com.Javas17Challenge.validation;

import com.Javas17Challenge.entity.Course;
import com.Javas17Challenge.exception.CourseException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CourseValidation {


    public static void name(String name) {

        if(null == name || name.isEmpty()) {
            throw new CourseException("name cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    public static void credit(Course course) {

        Integer credit = course.getCredit();
        if(credit == null) {
            throw new CourseException("course credit should be provided", HttpStatus.BAD_REQUEST);
        }

        if(!(0 <= credit &&  credit <= 4)) {
            throw new CourseException("course credit should be in [0,4] closed interval", HttpStatus.BAD_REQUEST);
        }

    }

    public static void uniqueName(Course course, List<Course> courses) {

        if(courses.stream()
                .anyMatch( c -> c.getName().equalsIgnoreCase( course.getName() ) )
        ) {
            throw new CourseException(String.format("Course %s is already in the courses list", course.getName()), HttpStatus.BAD_REQUEST);
        }
    }

    public static void idDoesNotExist(List<Course> courses, long id) {

        if( courses.stream().noneMatch( c -> c.getId() == id ) ) {
            throw new CourseException(String.format("Course with id: %d does not exist", id), HttpStatus.BAD_REQUEST);
        }
    }

    public static void idExists(List<Course> courses, long id) {

        if( courses.stream().anyMatch( c -> c.getId() == id ) ) {
            throw new CourseException(String.format("Course with id: %d already exists", id), HttpStatus.BAD_REQUEST);
        }
    }
}
