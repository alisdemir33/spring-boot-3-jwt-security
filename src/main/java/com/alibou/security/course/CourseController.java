package com.alibou.security.course;

import com.alibou.security.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

   // @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping
    public Course createCourse(@RequestBody Course course) {

        courseService.createCourseTest();

        return courseService.createCourse(course);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{courseId}/sections")
    public Section addSectionToCourse(@PathVariable Integer courseId, @RequestBody Section section) {
        return courseService.addSectionToCourse(courseId, section);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{courseId}/sections")
    public List<Section> getCourseSections(@PathVariable Integer courseId) {
        return courseService.getCourseSections(courseId);
    }
}