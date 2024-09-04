package com.library.management.repository;

import com.library.management.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT CASE WHEN COUNT(ub) > 0 THEN true ELSE false END FROM Transaction ub WHERE ub.book.id = :bookId AND ub.user.id = :userId")
    boolean existsByBookIdAndUserId(@Param("bookId") Long bookId, @Param("userId") Long userId);

}