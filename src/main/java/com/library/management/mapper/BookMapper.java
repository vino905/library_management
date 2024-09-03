package com.library.management.mapper;

import com.library.management.model.dto.BookDto;
import com.library.management.model.entity.Book;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookMapper {

    public static final Integer DEFUALT_BOOKS_COUNT = 10;
    public BookDto toDto(Book book) {
        return BookDto.builder().id(book.getId()).title(book.getTitle()).author(book.getAuthor()).totalCopies(book.getTotalCopies()).availableCopies(book.getAvailableCopies()).build();
    }


    public Book toEntity(BookDto bookDto) {
        return Book.builder().id(bookDto.getId()).title(bookDto.getTitle()).author(bookDto.getAuthor()).totalCopies(Objects.isNull(bookDto.getTotalCopies()) ? DEFUALT_BOOKS_COUNT : bookDto.getTotalCopies()).availableCopies(Objects.isNull(bookDto.getAvailableCopies()) &&  Objects.isNull(bookDto.getTotalCopies()) ? DEFUALT_BOOKS_COUNT : bookDto.getTotalCopies()).build();
    }

}
