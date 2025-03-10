package com.alibou.security.course;

import com.alibou.security.author.Author;
import com.alibou.security.author.dto.BaseAuthorDto;
import com.alibou.security.author.util.AuthorMapper;
import com.alibou.security.author.util.AuthorRepository;
import com.alibou.security.common.ResultDto;
import com.alibou.security.course.dto.BaseCourseDto;
import com.alibou.security.course.dto.CourseDto;
import com.alibou.security.course.dto.CourseRequest;
import com.alibou.security.course.dto.CourseSearchFormDto;
import com.alibou.security.course.util.CourseMapper;
import com.alibou.security.course.util.CourseRepository;
import com.alibou.security.course.util.CourseSpecification;
import com.alibou.security.section.Section;
import com.alibou.security.section.dto.BaseSectionDto;
import com.alibou.security.section.dto.SectionDto;
import com.alibou.security.section.util.SectionMapper;
import com.alibou.security.section.util.SectionRepository;
import com.alibou.security.utils.ConvertUtils;
import com.alibou.security.utils.QueryUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CourseDto save(CourseRequest request) {
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        Course savedCourse = courseRepository.save(course);
        return convertToDto(savedCourse);
    }

    @Transactional
    public Course createCourse(Course course, List<Integer> authorIds, List<Section> sections) {
        List<Author> authors = authorRepository.findAllById(authorIds).stream().collect(Collectors.toList());
        course.setAuthors(authors);
        Course savedCourse = courseRepository.save(course);

        for (Section section : sections) {
            section.setCourse(savedCourse);
            sectionRepository.save(section);
        }

        return savedCourse;
    }

    @Transactional
    public CourseDto createCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());

        List<Section> sections = courseRequest.getSections().stream().map(sectionDto -> {
            Section section = new Section();
            section.setName(sectionDto.getName());
            section.setSectionOrder(sectionDto.getSectionOrder());
            return section;
        }).collect(Collectors.toList());

        List<Author> authors = authorRepository.findAllById(courseRequest.getAuthorIds()).stream().collect(Collectors.toList());
        course.setAuthors(authors);

        Course savedCourse = courseRepository.save(course);

        for (Section section : sections) {
            section.setCourse(savedCourse);
            sectionRepository.save(section);
        }
       // Course savedCourse = createCourse(course, courseRequest.getAuthorIds(), sections);
        return convertToDto(savedCourse);
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

    public Section addSectionToCourse(Integer courseId, Section section) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
       // section.setCourse(course);
        return sectionRepository.save(section);
    }

    public List<Section> getCourseSections(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return course.getSections();
    }



    public CourseDto convertToDto(Course course) {
        CourseDto dto = courseMapper.toCourseDto(course);
        dto.setSections(mapSections(course.getSections()));
        dto.setAuthors(mapAuthors(course.getAuthors()));
        return dto;
    }

    public List<BaseSectionDto> mapSections(List<Section> sections) {
        return sections.stream()
                .map(sectionMapper::toBaseSectionDto)
                .collect(Collectors.toList());
    }

    public List<BaseAuthorDto> mapAuthors(List<Author> authors) {
        return authors.stream()
                .map(authorMapper::toBaseAuthorDto)
                .collect(Collectors.toList());
    }

    public CourseDto convertToDto2(Course course) {
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


    /*public void createCourseTest() {

        Course course1 = new Course();
        course1.setTitle("Course 1");
        createCourse(course1);

        Course course2 = new Course();
        course2.setTitle("Course 2");
        createCourse(course2);

      // SectionService sectionService = new SectionService();

        Section section1 = new Section();
        section1.setName("Section 1.1");
        //section1.set("Content for Section 1.1");
        sectionService.addSectionToCourse(course1.getId(), section1);

        Section section2 = new Section();
        section2.setName("Section 1.2");
        //section2.setContent("Content for Section 1.2");
        sectionService.addSectionToCourse(course1.getId(), section2);

        Section section3 = new Section();
        section3.setName("Section 1.3");
        //section3.setContent("Content for Section 1.3");
        sectionService.addSectionToCourse(course1.getId(), section3);

        Section section4 = new Section();
        section4.setName("Section 2.1");
       // section4.setContent("Content for Section 2.1");
        sectionService.addSectionToCourse(course2.getId(), section4);

        Section section5 = new Section();
        section5.setName("Section 2.2");
       // section5.setContent("Content for Section 2.2");
        sectionService.addSectionToCourse(course2.getId(), section5);

        Section section6 = new Section();
        section6.setName("Section 2.3");
       // section6.setContent("Content for Section 2.3");
        sectionService.addSectionToCourse(course2.getId(), section6);
        //return courseRepository.save(course);
    }*/

}