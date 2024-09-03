package com.library.management.service.impl;

import com.library.management.model.entity.Book;
import com.library.management.model.entity.Transaction;
import com.library.management.model.entity.User;
import com.library.management.model.enums.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TransactionFactory {
    public Transaction createTransaction(TransactionType transactionType, Book book, User user) {
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setUser(user);
        transaction.setTransactionType(transactionType);
        transaction.setIssueDate(LocalDateTime.now());

        if (transactionType.equals(TransactionType.RETURN)) {
            transaction.setReturnDate(LocalDateTime.now());
        }
        log.info("prepared transaction : {}", transaction);
        return transaction;
    }
}
