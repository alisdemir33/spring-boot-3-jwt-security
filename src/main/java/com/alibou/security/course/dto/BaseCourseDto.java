package com.alibou.security.course.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseCourseDto {
    private Integer id;
    private String title;
    private String description;
}
