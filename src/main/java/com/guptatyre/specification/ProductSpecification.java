package com.guptatyre.specification;

import com.guptatyre.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> filter(
            String keyword,
            Long brandId,
            Long categoryId,
            Boolean active) {

        return (root, query, cb) -> {

            var predicate = cb.conjunction();

            if (keyword != null && !keyword.isBlank()) {

                predicate = cb.and(predicate,
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + keyword.toLowerCase() + "%"));

            }

            if (brandId != null) {

                predicate = cb.and(predicate,
                        cb.equal(root.get("brand").get("id"), brandId));

            }

            if (categoryId != null) {

                predicate = cb.and(predicate,
                        cb.equal(root.get("category").get("id"), categoryId));

            }

            if (active != null) {

                predicate = cb.and(predicate,
                        cb.equal(root.get("active"), active));

            }

            return predicate;

        };

    }

}
