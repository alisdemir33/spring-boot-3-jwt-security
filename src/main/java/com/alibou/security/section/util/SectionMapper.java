package com.alibou.security.section.util;


import com.alibou.security.section.Section;
import com.alibou.security.section.dto.BaseSectionDto;
import com.alibou.security.section.dto.SectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SectionMapper {
    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

    // Base mapping without relationships
    BaseSectionDto toBaseSectionDto(Section section);

    // First level relationship only
//    @Mapping(target = "course", expression = "java(courseMapper.toBaseCourseDto(section.getCourse()))")
//    @Mapping(target = "lectures", expression = "java(section.getLectures().stream().map(lectureMapper::toBaseLectureDto).collect(Collectors.toList()))")
//    SectionDto toSectionDto(Section section);
}