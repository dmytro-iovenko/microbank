package com.microbank.account.query.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.microbank.account.query.dto.EqualityType;
import com.microbank.account.query.entities.Account;
import com.microbank.account.query.queries.FindAccountByIdHolderQuery;
import com.microbank.account.query.queries.FindAccountByIdQuery;
import com.microbank.account.query.queries.FindAccountsWithBalanceQuery;
import com.microbank.account.query.queries.FindAllAccountsQuery;
import com.microbank.account.query.repositories.AccountRepository;

public class QueryHandlerImpl implements QueryHandler {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> handle(FindAllAccountsQuery query) {
        Iterable<Account> accounts = accountRepository.findAll();
        List<Account> accountsList = new ArrayList<>();
        accounts.forEach(accountsList::add);
        return accountsList;
    }

    @Override
    public List<Account> handle(FindAccountByIdQuery query) {
        Optional<Account> account = accountRepository.findById(query.getId());
        if (account.isEmpty()) return null;
        List<Account> accountsList = new ArrayList<>();
        accountsList.add(account.get());
        return accountsList;
    }

    @Override
    public List<Account> handle(FindAccountByIdHolderQuery query) {
        Optional<Account> account = accountRepository.findByAccountHolder(query.getAccountHolder());
        if (account.isEmpty()) return null;
        List<Account> accountsList = new ArrayList<>();
        accountsList.add(account.get());
        return accountsList;
    }

    @Override
    public List<Account> handle(FindAccountsWithBalanceQuery query) {
        List<Account> bankAccountsList = query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());
        return bankAccountsList;
    }
    
}
