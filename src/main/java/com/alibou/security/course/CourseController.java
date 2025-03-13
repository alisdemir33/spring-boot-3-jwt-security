package com.alibou.security.course;

import com.alibou.security.author.dto.BaseAuthorDto;
import com.alibou.security.common.ResultDto;
import com.alibou.security.course.dto.CourseDto;
import com.alibou.security.course.dto.CourseRequest;
import com.alibou.security.course.dto.CourseSearchFormDto;
import com.alibou.security.section.Section;
import com.alibou.security.section.dto.BaseSectionDto;
import com.alibou.security.section.dto.SectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Integer id) {
        CourseDto courseDto = courseService.getCourseById(id);
        return ResponseEntity.ok(courseDto);
    }

    @GetMapping
    public ResponseEntity<ResultDto<CourseDto>> getAllCourses() {
        ResultDto<CourseDto> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<ResultDto<CourseDto>> searchCourses(@RequestBody CourseSearchFormDto searchDto) {
        ResultDto<CourseDto> result = courseService.findCoursesByCriteria(searchDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseRequest courseRequest) {
        CourseDto courseDto = courseService.createFullCourse(courseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
    }

    @PostMapping("/{courseId}/sections")
    public ResponseEntity<SectionDto> addSectionToCourse(@PathVariable Integer courseId, @RequestBody SectionDto sectionDto) {
        SectionDto savedSectionDto =  courseService.addSectionToCourse(courseId, sectionDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedSectionDto);
    }

    @GetMapping("/{courseId}/sections")
    public ResponseEntity<ResultDto<BaseSectionDto>> getCourseSections(@PathVariable Integer courseId) {
        ResultDto<BaseSectionDto> result = courseService.getCourseSections(courseId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{courseId}/authors")
    public ResponseEntity<ResultDto<BaseAuthorDto>> getCourseAuthors(@PathVariable Integer courseId) {
        ResultDto<BaseAuthorDto> result = courseService.getCourseAuthors(courseId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Integer id, @RequestBody CourseRequest request) {
        CourseDto updatedCourse = courseService.updateCourse(id, request);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}