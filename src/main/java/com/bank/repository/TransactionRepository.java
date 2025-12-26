package com.bank.repository;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Transaction;
import com.bank.domain.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionId(String transactionId);
    Optional<Transaction> findByIdempotencyKey(String idempotencyKey);
    Page<Transaction> findByAccount(Account account, Pageable pageable);
    
    @Query("SELECT t FROM Transaction t WHERE t.account = :account " +
           "AND (:type IS NULL OR t.transactionType = :type) " +
           "AND (:startDate IS NULL OR t.transactionDate >= :startDate) " +
           "AND (:endDate IS NULL OR t.transactionDate <= :endDate) " +
           "AND (:searchTerm IS NULL OR t.description LIKE %:searchTerm%)")
    Page<Transaction> findWithFilters(
        @Param("account") Account account,
        @Param("type") TransactionType type,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("searchTerm") String searchTerm,
        Pageable pageable
    );
}
