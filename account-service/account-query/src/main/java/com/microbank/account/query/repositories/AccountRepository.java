package com.microbank.account.query.repositories;

import org.springframework.data.repository.CrudRepository;

import com.microbank.account.query.entities.Account;

public interface AccountRepository extends CrudRepository<Account, String> {
}
