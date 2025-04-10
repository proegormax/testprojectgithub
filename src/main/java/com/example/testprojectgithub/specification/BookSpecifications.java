package com.example.testprojectgithub.specification;

import com.example.testprojectgithub.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> filterBy(String title, String brand, Integer year) {
        return Specification.where(titleContains(title))
                .and(brandEquals(brand))
                .and(yearEquals(year));
    }

    public static Specification<Book> titleContains(String title) {
        return (root, query, cb) ->
                title == null || title.isEmpty() ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> brandEquals(String brand) {
        return (root, query, cb) ->
                brand == null || brand.isEmpty() ? null : cb.equal(cb.lower(root.get("brand")), brand.toLowerCase());
    }

    public static Specification<Book> yearEquals(Integer year) {
        return (root, query, cb) ->
                year == null ? cb.conjunction() : cb.equal(root.get("publishYear"), year);
    }

}
