package com.example.testprojectgithub.service.impl;

import com.example.testprojectgithub.dto.BookDto;
import com.example.testprojectgithub.entity.Book;
import com.example.testprojectgithub.mapper.BookMapper;
import com.example.testprojectgithub.repository.BookRepository;
import com.example.testprojectgithub.service.BookService;
import com.example.testprojectgithub.specification.BookSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public BookServiceImpl(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BookDto save(BookDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public BookDto update(Long id, BookDto dto) {
        Book entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        Book updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<BookDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Page<BookDto> findAll(String title, String brand, Integer year, Pageable pageable) {
        return repository.findAll(BookSpecifications.filterBy(title, brand, year), pageable)
                .map(mapper::toDto);
    }

}
