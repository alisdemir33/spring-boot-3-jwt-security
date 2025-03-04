package com.alibou.security.course.dto;

import com.alibou.security.common.SearchFormDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearchFormDto extends SearchFormDto {
    private Integer id;
    private String title;
    private String description;
}