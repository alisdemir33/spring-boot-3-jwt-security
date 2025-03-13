package com.alibou.security.course;

import com.alibou.security.author.Author;
import com.alibou.security.author.dto.BaseAuthorDto;
import com.alibou.security.author.util.AuthorMapper;
import com.alibou.security.author.util.AuthorRepository;
import com.alibou.security.common.ResultDto;
import com.alibou.security.course.dto.CourseDto;
import com.alibou.security.course.dto.CourseRequest;
import com.alibou.security.course.dto.CourseSearchFormDto;
import com.alibou.security.course.util.CourseMapper;
import com.alibou.security.course.util.CourseRepository;
import com.alibou.security.course.util.CourseSpecification;
import com.alibou.security.exception.EntityNotFoundException;
import com.alibou.security.section.Section;
import com.alibou.security.section.dto.BaseSectionDto;
import com.alibou.security.section.dto.SectionDto;
import com.alibou.security.section.util.SectionMapper;
import com.alibou.security.section.util.SectionMapperDetailed;
import com.alibou.security.section.util.SectionRepository;
import com.alibou.security.session.SessionService;
import com.alibou.security.utils.ConvertUtils;
import com.alibou.security.utils.QueryUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    private final AuthorRepository authorRepository;
    private final CourseMapper courseMapper;
    private final SectionMapper sectionMapper;
    private final AuthorMapper authorMapper;
    private final SessionService sessionService;
    private final SectionMapperDetailed sectionMapperDetailed;

    public ResultDto<CourseDto> findCoursesByCriteria(CourseSearchFormDto searchDto) {
        var pageable = QueryUtils.getPageable(searchDto, "id");

        Specification<Course> spec = Specification.where(CourseSpecification.hasTitle(searchDto.getTitle()))
                .and(CourseSpecification.hasDescription(searchDto.getDescription()));

        Page<Course> page = courseRepository.findAll(spec, pageable);

        return ConvertUtils.sliceToResponseDtoFunction.apply(page.map(this::convertToDto));
    }

    public CourseDto getCourseById(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        return convertToDto(course);

    }

    public ResultDto<CourseDto> getAllCourses() {
        List<CourseDto> lst = courseRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return ConvertUtils.listToResponseDtoFunction.apply(lst);
    }

    // For full course creation with relationships
    @Transactional
    public CourseDto createFullCourse(CourseRequest request) {
        // 1. Validate request
        validateCourseRequest(request);

        // 2. Create and save the course
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        String currentUser = sessionService.getCurrentUser();
        course.setCreatedBy(currentUser);

        // 3. Handle relationships
        if (!CollectionUtils.isEmpty(request.getAuthorIds())) {
            List<Author> authors = authorRepository.findAllById(request.getAuthorIds());
            validateAuthors(request.getAuthorIds(), authors);
            course.setAuthors(authors);
        }

        Course savedCourse = courseRepository.save(course);

        // 4. Handle sections in batch
        if (!CollectionUtils.isEmpty(request.getSections())) {
            List<Section> sections = request.getSections().stream()
                    .map(sectionDto -> {
                        Section section = new Section();
                        section.setName(sectionDto.getName());
                        section.setSectionOrder(sectionDto.getSectionOrder());
                        section.setCourse(savedCourse);
                        return section;
                    })
                    .collect(Collectors.toList());
            sectionRepository.saveAll(sections);
        }

        return courseMapper.toCourseDto(savedCourse);
    }

    private void validateCourseRequest(CourseRequest request) {
        if (request == null) {
            throw new EntityNotFoundException("Course request cannot be null");
        }
        // Add more validation as needed
    }

    private void validateAuthors(List<Integer> requestedIds, List<Author> foundAuthors) {
        if (foundAuthors.size() != requestedIds.size()) {
            throw new EntityNotFoundException("Some authors were not found");
        }
    }

    @Transactional
    public CourseDto updateCourse(Integer id, CourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        Course updatedCourse = courseRepository.save(course);
        return convertToDto(updatedCourse);
    }

    @Transactional
    public void deleteCourse(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
    }

    @Transactional
    public SectionDto addSectionToCourse(Integer courseId, SectionDto sectionDto) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Section section = sectionMapper.toSection(sectionDto);
        section.setCourse(course);
        Section savedSection = sectionRepository.save(section);
        return sectionMapperDetailed.toSectionDto(savedSection);
    }

    public ResultDto<BaseSectionDto> getCourseSections(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        List<BaseSectionDto> lst = course.getSections().stream()
                .map(sectionMapper::toBaseSectionDto)
                .collect(Collectors.toList());
       return ConvertUtils.listToResponseDtoFunction.apply(lst);
    }

    public ResultDto<BaseAuthorDto> getCourseAuthors(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        List<BaseAuthorDto> lst = course.getAuthors().stream()
                .map(authorMapper::toBaseAuthorDto)
                .collect(Collectors.toList());

        ResultDto<BaseAuthorDto> list = ConvertUtils.listToResponseDtoFunction.apply(lst);
        return list;
    }

    public CourseDto convertToDto(Course course) {
        CourseDto dto = courseMapper.toCourseDto(course);
        dto.setSections(course.getSections() != null ? mapSections(course.getSections()) : Collections.emptyList());
        dto.setAuthors(course.getAuthors() != null ? mapAuthors(course.getAuthors()) : Collections.emptyList());
        return dto;
    }

    public List<BaseSectionDto> mapSections(List<Section> sections) {
        return sections.stream()
                .map(sectionMapper::toBaseSectionDto)
                .collect(Collectors.toList());
    }

    public List<BaseAuthorDto> mapAuthors(List<Author> authors) {
        List<BaseAuthorDto> lst = authors.stream()
                .map(authorMapper::toBaseAuthorDto)
                .collect(Collectors.toList());
        return  lst;
    }

    public CourseDto convertToDtoManual(Course course) {
        if (course == null) {
            return null;
        }
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .sections(course.getSections().stream()
                        .map(section -> SectionDto.builder()
                                .id(section.getId())
                                .name(section.getName())
                                .sectionOrder(section.getSectionOrder())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }




}