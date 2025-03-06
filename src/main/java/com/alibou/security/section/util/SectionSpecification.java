package com.alibou.security.section.util;

import com.alibou.security.section.Section;
import org.springframework.data.jpa.domain.Specification;

public class SectionSpecification {

    public static Specification<Section> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Section> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("description"), description);
    }
}