package com.alibou.security.section.util;

import com.alibou.security.lecture.Lecture;
import com.alibou.security.lecture.dto.LectureDto;
import com.alibou.security.section.Section;
import com.alibou.security.section.dto.SectionDto;
import com.alibou.security.course.dto.CourseDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SectionMapper {

    public LectureDto convertToLectureDto(Lecture lecture) {
        if (lecture == null) {
            return null;
        }
        return LectureDto.builder()
                .id(lecture.getId())
                .name(lecture.getName())
                .description(lecture.getDescription())
                .build();
    }

    public SectionDto convertToDto(Section section, CourseDto courseDto) {
        if (section == null) {
            return null;
        }
        return SectionDto.builder()
                .id(section.getId())
                .name(section.getName())
                .description(section.getDescription())
                .sectionOrder(section.getSectionOrder())
                .course(courseDto)
                .lectures(section.getLectures().stream()
                        .map(this::convertToLectureDto)
                        .collect(Collectors.toList()))
                .build();
    }
}