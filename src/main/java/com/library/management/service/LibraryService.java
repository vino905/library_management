package com.library.management.service;

import com.library.management.model.dto.BookDto;

import java.util.List;

public interface LibraryService {
    void issueBook(Long bookId, Long userId);

    void returnBook(Long bookId, Long userId);

    List<BookDto> getAllBooks();

    List<BookDto> searchBooksByTitle(String title);

    BookDto addBook(BookDto bookDto);
}
