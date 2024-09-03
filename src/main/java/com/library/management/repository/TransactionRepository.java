package com.library.management.repository;

import com.library.management.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsByBookIdAndUserId(Long bookId, Long userId);
}