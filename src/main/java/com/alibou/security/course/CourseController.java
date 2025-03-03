package com.alibou.security.course;

import com.alibou.security.book.Book;
import com.alibou.security.section.Section;
import com.alibou.security.section.SectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public Course createCourse(@RequestBody CourseRequest courseRequest) {
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());

        List<Section> sections = courseRequest.getSections().stream().map(sectionRequest -> {
            Section section = new Section();
            section.setName(sectionRequest.getName());
            section.setSectionOrder(sectionRequest.getSectionOrder());
            return section;
        }).collect(Collectors.toList());

        return courseService.createCourse(course, courseRequest.getAuthorIds(), sections);
    }

    @GetMapping
    public String getAllCourses() {
        return "GET:: All courses";
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto>  getCourse(@PathVariable Integer id) {
        Course course = courseService.getCourseById(id);
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        courseDto.setDescription(course.getDescription());
        courseDto.setSections(course.getSections().stream().map(section -> {
            SectionDto sectionDto = new SectionDto();
            sectionDto.setId(section.getId());
            sectionDto.setName(section.getName());
            sectionDto.setSectionOrder(section.getSectionOrder());
            return sectionDto;
        }).collect(Collectors.toList()));

        return  ResponseEntity.ok(courseDto);
    }



//    @PostMapping("/{courseId}/sections")
//    public Section addSectionToCourse(@PathVariable Integer courseId, @RequestBody Section section) {
//        return courseService.addSectionToCourse(courseId, section);
//    }
//
//
//    @GetMapping("/{courseId}/sections")
//    public List<Section> getCourseSections(@PathVariable Integer courseId) {
//        return courseService.getCourseSections(courseId);
//    }
}