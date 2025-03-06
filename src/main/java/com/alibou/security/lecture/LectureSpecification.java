package com.alibou.security.lecture;

import org.springframework.data.jpa.domain.Specification;

public class LectureSpecification {

    public static Specification<Lecture> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Lecture> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("description"), description);
    }

    public static Specification<Lecture> belongsToSection(Integer sectionId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("section").get("id"), sectionId);
    }
}