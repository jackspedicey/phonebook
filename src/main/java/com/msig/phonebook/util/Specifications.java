package com.msig.phonebook.util;

import com.msig.phonebook.entity.ContactPerson;
import org.springframework.data.jpa.domain.Specification;

public class Specifications {
    public static Specification<ContactPerson> contactSearch(String search) {
        return (root, query, builder) -> {
            if (search == null || search.isEmpty()) {
                return builder.conjunction();
            }
            String likePattern = "%" + search.toLowerCase() + "%";

            return builder.or(
                    builder.like(builder.lower(root.get("name")), likePattern),
                    builder.like(builder.lower(root.get("phone")), likePattern),
                    builder.like(builder.lower(root.get("email")), likePattern),
                    builder.like(builder.lower(root.get("company")), likePattern),
                    builder.like(builder.lower(root.get("title")), likePattern)
            );
        };
    }
}