package com.alibou.security.author.util;

import com.alibou.security.author.Author;
import com.alibou.security.author.dto.BaseAuthorDto;
import com.alibou.security.author.dto.AuthorDto;
import com.alibou.security.course.util.CourseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(uses = {CourseMapper.class})
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    BaseAuthorDto toBaseAuthorDto(Author author);

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "firstName", source = "firstName")
//    @Mapping(target = "lastName", source = "lastName")
//    @Mapping(target = "email", source = "email")
//    @Mapping(target = "courses", expression = "java(author.getCourses().stream().map(courseMapper::toCourseDto).collect(Collectors.toList()))")
//    AuthorDto toAuthorDto(Author author);
}