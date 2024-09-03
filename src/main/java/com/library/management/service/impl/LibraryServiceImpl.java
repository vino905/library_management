package com.library.management.service.impl;

import com.library.management.exception.InValidRequestException;
import com.library.management.exception.ResourceNotFoundException;
import com.library.management.mapper.BookMapper;
import com.library.management.model.dto.BookDto;
import com.library.management.model.entity.Book;
import com.library.management.model.entity.Transaction;
import com.library.management.model.entity.User;
import com.library.management.model.enums.TransactionType;
import com.library.management.repository.LibraryRepository;
import com.library.management.repository.TransactionRepository;
import com.library.management.repository.UserRepository;
import com.library.management.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;

    @Override
    public void issueBook(Long bookId, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = libraryRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (book.getAvailableCopies().equals(0)) {
            log.error("error occurred while issuing book. As book {} is not available", book.getTitle());
            throw new ResourceNotFoundException("Book is not available currently");

        }

        Transaction transaction = transactionFactory.createTransaction(TransactionType.ISSUE, book, user);
        transactionRepository.save(transaction);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        libraryRepository.save(book);
    }

    @Override
    public void returnBook(Long bookId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = libraryRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (book.getAvailableCopies().equals(0)) {
            log.error("error occurred while returning book. As book {} is not available", book.getTitle());
            throw new ResourceNotFoundException("Book is not available currently");
        }

        if (!transactionRepository.existsByBookIdAndUserId(bookId, userId)) {
            log.error("error occurred while returning book. As book with Id : {} is not issued to this user with Id : {}", bookId, userId);
            throw new InValidRequestException(String.format("error occurred while returning book. As book with Id : %s is not issued to this user with Id : %s", bookId, userId));
        }
        Transaction transaction = transactionFactory.createTransaction(TransactionType.RETURN, book, user);
        transactionRepository.save(transaction);
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        libraryRepository.save(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return libraryRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    @Override
    public List<BookDto> searchBooksByTitle(String title) {
        return libraryRepository.findByTitleContainingIgnoreCase(title).stream().map(bookMapper::toDto).toList();
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        libraryRepository.save(book);
        return bookMapper.toDto(book);
    }
}
