package com.alibou.security.course.util;

import com.alibou.security.author.util.AuthorMapper;

import com.alibou.security.course.Course;
import com.alibou.security.course.dto.BaseCourseDto;
import com.alibou.security.course.dto.CourseDto;
import com.alibou.security.section.util.SectionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {SectionMapper.class, AuthorMapper.class})
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    BaseCourseDto toBaseCourseDto(Course course);

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "title", source = "title")
//    @Mapping(target = "description", source = "description")
//    @Mapping(target = "authors", expression = "java(authorMapper.toAuthorDto(course.getAuthors()))")
//    @Mapping(target = "sections", expression = "java(course.getSections().stream().map(sectionMapper::toBaseSectionDto).collect(Collectors.toList()))")
//    CourseDto toCourseDto(Course course);
}