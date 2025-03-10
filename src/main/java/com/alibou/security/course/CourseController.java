package com.alibou.security.course;

import com.alibou.security.common.ResultDto;
import com.alibou.security.course.dto.CourseDto;
import com.alibou.security.course.dto.CourseRequest;
import com.alibou.security.course.dto.CourseSearchFormDto;
import com.alibou.security.section.Section;
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
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/search")
    public ResponseEntity<ResultDto<CourseDto>> searchCourses(@RequestBody CourseSearchFormDto searchDto) {
        ResultDto<CourseDto> result = courseService.findCoursesByCriteria(searchDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<CourseDto> save(@RequestBody CourseRequest request) {
        CourseDto courseDto = courseService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
    }

    @PostMapping("/create")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseRequest courseRequest) {
        CourseDto courseDto = courseService.createCourse(courseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
    }

    @PostMapping("/{courseId}/sections")
    public Section addSectionToCourse(@PathVariable Integer courseId, @RequestBody Section section) {
        return courseService.addSectionToCourse(courseId, section);
    }

    @GetMapping("/{courseId}/sections")
    public List<Section> getCourseSections(@PathVariable Integer courseId) {
        return courseService.getCourseSections(courseId);
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