package com.alibou.security.section;

import com.alibou.security.course.Course;
import com.alibou.security.course.util.CourseRepository;
import com.alibou.security.course.CourseService;
import com.alibou.security.exception.EntityNotFoundException;
import com.alibou.security.section.dto.BaseSectionDto;
import com.alibou.security.section.util.*;
import com.alibou.security.section.dto.SectionDto;
import com.alibou.security.section.dto.SectionRequest;
import com.alibou.security.section.dto.SectionSearchFormDto;
import com.alibou.security.session.SessionService;
import com.alibou.security.utils.QueryUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final SessionService sessionService;
    private final CourseService courseService;
   // private final SectionMapper sectionMapper = SectionMapper.INSTANCE;
    private    final    SectionMapper sectionMapper;
    private    final SectionMapperDetailed sectionMapperDetailed;

        public SectionDto getSectionById(Integer id) {
        Optional<Section> section= sectionRepository.findById(id);
         return  section.map(sect -> sectionMapperDetailed.toSectionDto(sect))
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
    }


//    public BaseSectionDto getSectionById(Integer id) {
//        Optional<Section> section = sectionRepository.findById(id);
//             return   section.map(sectionMapper::toBaseSectionDto)
//                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
//    }

    public BaseSectionDto getBasicSectionInfo(Integer id) {
        return sectionMapper.toBaseSectionDto(
                sectionRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Section not found"))
        );
    }

    public BaseSectionDto getSectionWithFirstLevel(Integer id) {
        return sectionMapper.toBaseSectionDto(
                sectionRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Section not found"))
        );
    }

    // Manual control for deeper relationships
    public BaseSectionDto getSectionWithFullDetails(Integer id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));

        BaseSectionDto dto = sectionMapper.toBaseSectionDto(section);
        // Manually load additional levels if needed
        return dto;
    }


    public List<BaseSectionDto> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(sectionMapper::toBaseSectionDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SectionDto createSection(SectionRequest sectionRequest) {
        String currentUser = sessionService.getCurrentUser();

        Section section = Section.builder()
                .name(sectionRequest.getName())
                .description(sectionRequest.getDescription())
                .sectionOrder(sectionRequest.getSectionOrder())
                .createdBy(currentUser)
                .build();
        Integer courseId= sectionRequest.getCourseId();
        Section createdSection = addSectionToCourse(courseId, section);
        return sectionMapperDetailed.toSectionDto(createdSection);
    }

    @Transactional
    public BaseSectionDto updateSection(Integer id, SectionRequest sectionRequest) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
        section.setName(sectionRequest.getName());
        section.setDescription(sectionRequest.getDescription());
        section.setSectionOrder(sectionRequest.getSectionOrder());
        section.setLastModifiedBy(sessionService.getCurrentUser());
        Section result = sectionRepository.save(section);
        return sectionMapper.toBaseSectionDto(result);
    }

    @Transactional
    public void deleteSection(Integer id) {
        sectionRepository.deleteById(id);
    }



    @Transactional
    public Section addSectionToCourse(Integer courseId, Section section) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        section.setCourse(course);
        // an empty array list is of Lectures set for not getting null pointer when mapping.
        section.setLectures(new ArrayList<>());
        return sectionRepository.save(section);
    }

    public List<Section> getSectionsByCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        return course.getSections();
    }

    public List<BaseSectionDto> searchSections(SectionSearchFormDto searchForm) {
        var pageable = QueryUtils.getPageable(searchForm, "id");

        Specification<Section> spec = Specification.where(SectionSpecification.hasName(searchForm.getName()))
                .and(SectionSpecification.hasDescription(searchForm.getDescription()));

        Page<Section> page = sectionRepository.findAll(spec, pageable);

//        return page.stream()
//                .map(section -> sectionMapper.convertToDto(section, courseService.convertToDto(section.getCourse())))
//                .collect(Collectors.toList());
        return page.stream()
                .map(sectionMapper::toBaseSectionDto)
                .collect(Collectors.toList());
    }
}