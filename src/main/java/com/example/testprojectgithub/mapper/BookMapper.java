package com.example.testprojectgithub.mapper;

import com.example.testprojectgithub.dto.BookDto;
import com.example.testprojectgithub.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);
    Book toEntity(BookDto bookDto);
}
