package com.bank.repository;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.User;
import com.bank.domain.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByUser(User user);
    List<Account> findByUserAndStatus(User user, AccountStatus status);
    boolean existsByAccountNumber(String accountNumber);
}
