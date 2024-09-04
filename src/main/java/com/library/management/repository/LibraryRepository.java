package com.library.management.repository;

import com.library.management.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Book,Long> {


    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> searchBooksByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b")
    List<Book> getAllBooks();

    @Query("SELECT b FROM Book b WHERE b.id = :id")
    Book findBookById(@Param("id") Long id);


}
