package com.alibou.security.author.dto;

import com.alibou.security.course.dto.CourseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
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
