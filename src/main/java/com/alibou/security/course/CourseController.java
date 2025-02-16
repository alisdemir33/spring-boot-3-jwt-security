package com.alibou.security.course;

import com.alibou.security.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
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