package com.microbank.account.query.handlers;

import java.util.List;

import com.microbank.account.query.entities.Account;
import com.microbank.account.query.queries.FindAccountByIdHolderQuery;
import com.microbank.account.query.queries.FindAccountByIdQuery;
import com.microbank.account.query.queries.FindAccountsWithBalanceQuery;
import com.microbank.account.query.queries.FindAllAccountsQuery;

public interface QueryHandler {
    List<Account> handle(FindAllAccountsQuery query);
    List<Account> handle(FindAccountByIdQuery query);
    List<Account> handle(FindAccountByIdHolderQuery query);
    List<Account> handle(FindAccountsWithBalanceQuery query);
}
