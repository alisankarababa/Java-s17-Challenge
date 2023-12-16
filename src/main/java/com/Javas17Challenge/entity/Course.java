package com.Javas17Challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    private Long id;
    private String name;
    private Integer credit;
    private Grade grade;
}
