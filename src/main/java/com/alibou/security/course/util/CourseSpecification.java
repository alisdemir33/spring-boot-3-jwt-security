package com.alibou.security.course.util;

import com.alibou.security.course.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {

    public static Specification<Course> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                title != null ? criteriaBuilder.like(root.get("title"), "%" + title + "%") : null;
    }

    public static Specification<Course> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                description != null ? criteriaBuilder.like(root.get("description"), "%" + description + "%") : null;
    }
}