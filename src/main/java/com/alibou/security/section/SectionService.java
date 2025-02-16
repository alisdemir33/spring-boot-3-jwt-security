package com.alibou.security.section;

import com.alibou.security.course.Course;
import com.alibou.security.course.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionService {


    private final SectionRepository sectionRepository;


    private final CourseRepository courseRepository;

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public Section addSectionToCourse(Integer courseId, Section section) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        section.setCourse(course);
        return sectionRepository.save(section);
    }

    public List<Section> getSectionsByCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return course.getSections();
    }
}