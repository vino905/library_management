package com.library.management.controller;

import com.library.management.model.dto.BookDto;
import com.library.management.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@AllArgsConstructor
public class LibraryController {


    private LibraryService libraryService;

    @PostMapping("/add/book")
    public ResponseEntity<BookDto> issueBook(@RequestBody BookDto bookDto) {

        return ResponseEntity.ok(libraryService.addBook(bookDto));
    }

    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestParam Long bookId, @RequestParam Long userId) {
        libraryService.issueBook(bookId, userId);
        return ResponseEntity.ok("Book issued successfully.");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long bookId, @RequestParam Long userId) {
        libraryService.returnBook(bookId, userId);
        return ResponseEntity.ok("Book returned successfully.");
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> listBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchBooks(@RequestParam String title) {
        return ResponseEntity.ok(libraryService.searchBooksByTitle(title));
    }
}
