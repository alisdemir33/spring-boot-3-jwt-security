package com.alibou.security.author.dto;

import com.alibou.security.course.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private List<CourseDto> courses;
    }
