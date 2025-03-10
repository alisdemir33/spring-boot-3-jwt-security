package com.alibou.security.author.util;

import com.alibou.security.author.Author;
import com.alibou.security.author.dto.BaseAuthorDto;
import com.alibou.security.author.dto.AuthorDto;
import com.alibou.security.course.util.CourseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AuthorMapper {
   // AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "age", source = "age")
    public abstract BaseAuthorDto toBaseAuthorDto(Author author);
}