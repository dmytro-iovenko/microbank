package com.microbank.account.query.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.microbank.account.query.entities.Account;

public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByAccountHolder(String accountHolder);
    List<Account> findByBalanceGreaterThan(double balance);
    List<Account> findByBalanceLessThan(double balance);
}
