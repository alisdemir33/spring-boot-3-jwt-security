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
public abstract class SectionMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "sectionOrder", source = "sectionOrder")
    public abstract BaseSectionDto toBaseSectionDto(Section section);


    public Section toSection(SectionDto sectionDto) {
        if (sectionDto == null) {
            return null;
        }
        Section section = new Section();
        section.setId(sectionDto.getId());
        section.setName(sectionDto.getName());
        section.setDescription(sectionDto.getDescription());
        section.setSectionOrder(sectionDto.getSectionOrder());
        return section;
    }
}

