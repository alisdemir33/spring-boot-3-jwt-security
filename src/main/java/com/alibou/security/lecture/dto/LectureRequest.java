package com.alibou.security.lecture.dto;

import lombok.Data;

@Data
public class LectureRequest {
    private String name;
    private String description;
    private Integer sectionId;
    private Integer resourceId;
}