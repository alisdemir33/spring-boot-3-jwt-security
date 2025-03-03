package com.alibou.security.section;

import lombok.Data;

@Data
public class SectionRequest {
    private String name;
    private Integer sectionOrder;
    private String description;
    private Integer courseId;
}