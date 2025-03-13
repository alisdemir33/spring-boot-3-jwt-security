package com.alibou.security.author.util;

import com.alibou.security.author.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {

    public static Specification<Author> hasAgeEqualTo(Integer age) {
        return (root, query, criteriaBuilder) ->
                (age != null && age != 0 ) ? criteriaBuilder.equal(root.get("age"), age) : null;
    }

    public static Specification<Author> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName != null ? criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%") : null;
    }

    public static Specification<Author> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName != null ? criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%") : null;
    }

    public static Specification<Author> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email != null ? criteriaBuilder.equal(root.get("email"), email) : null;
    }
}
