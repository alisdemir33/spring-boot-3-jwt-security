package com.alibou.security.course;

import com.alibou.security.section.Section;
import com.alibou.security.section.SectionRepository;
import com.alibou.security.section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Section addSectionToCourse(Integer courseId, Section section) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        section.setCourse(course);
        return sectionRepository.save(section);
    }

    public List<Section> getCourseSections(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return course.getSections();
    }

    public void createCourseTest() {
        CourseService courseService = new CourseService();

        Course course1 = new Course();
        course1.setTitle("Course 1");
        courseService.createCourse(course1);

        Course course2 = new Course();
        course2.setTitle("Course 2");
        courseService.createCourse(course2);

        SectionService sectionService = new SectionService();

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
    }

}