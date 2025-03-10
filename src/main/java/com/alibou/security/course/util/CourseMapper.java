package com.alibou.security.course.util;

import com.alibou.security.author.util.AuthorMapper;
import com.alibou.security.course.Course;
import com.alibou.security.course.dto.BaseCourseDto;
import com.alibou.security.course.dto.CourseDto;
import com.alibou.security.section.util.SectionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {SectionMapper.class})
public abstract class CourseMapper {
    public static final CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    public abstract CourseDto toCourseDto(Course course);

}