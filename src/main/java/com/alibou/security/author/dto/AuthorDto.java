package com.alibou.security.author.dto;

import com.alibou.security.course.dto.CourseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto extends BaseAuthorDto {
       private List<CourseDto> courses;
    }
