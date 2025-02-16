package com.alibou.security.course;

import com.alibou.security.section.SectionRequest;
import lombok.Data;

import java.util.List;

@Data
public class CourseRequest {
    private String title;
    private String description;
    private List<Integer> authorIds;
    private List<SectionRequest> sections;
}