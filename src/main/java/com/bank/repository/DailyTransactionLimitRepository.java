package com.bank.repository;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.DailyTransactionLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyTransactionLimitRepository extends JpaRepository<DailyTransactionLimit, Long> {
    Optional<DailyTransactionLimit> findByAccountAndTransactionDate(Account account, LocalDate date);
}
