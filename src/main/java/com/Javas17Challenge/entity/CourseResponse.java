package com.Javas17Challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CourseResponse {

    private Course course;
    private Integer totalGpa;
}
