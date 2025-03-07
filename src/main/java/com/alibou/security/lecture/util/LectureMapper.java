package com.alibou.security.lecture.util;

import com.alibou.security.lecture.Lecture;
import com.alibou.security.lecture.dto.LectureDto;
import com.alibou.security.section.util.SectionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {SectionMapper.class})
public abstract class LectureMapper {

    @Autowired
    protected SectionMapper sectionMapper;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "section", expression = "java(sectionMapper.toBaseSectionDto(lecture.getSection()))")
    public abstract LectureDto toLectureDto(Lecture lecture);
}

