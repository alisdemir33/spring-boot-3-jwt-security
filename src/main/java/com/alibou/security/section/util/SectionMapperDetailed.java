package com.alibou.security.section.util;

import com.alibou.security.course.util.CourseMapper;
import com.alibou.security.lecture.util.LectureMapper;
import com.alibou.security.section.Section;
import com.alibou.security.section.dto.BaseSectionDto;
import com.alibou.security.section.dto.SectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {LectureMapper.class,CourseMapper.class})
public abstract class SectionMapperDetailed {

    @Autowired
    protected CourseMapper courseMapper;

    @Autowired
    protected LectureMapper lectureMapper;

    @Mapping(target = "course", expression = "java(courseMapper.toCourseDto(section.getCourse()))")
    @Mapping(target = "lectures", expression = "java(section.getLectures().stream().map(lectureMapper::toLectureDto).collect(java.util.stream.Collectors.toList()))")
    public abstract SectionDto toSectionDto(Section section);
}
