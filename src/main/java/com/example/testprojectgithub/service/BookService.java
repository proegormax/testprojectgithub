package com.example.testprojectgithub.service;

import com.example.testprojectgithub.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {
    BookDto save(BookDto dto);
    BookDto update(Long id, BookDto dto);
    void delete(Long id);
    Optional<BookDto> findById(Long id);
    Page<BookDto> findAll(String title, String brand, Integer year, Pageable pageable);
}
