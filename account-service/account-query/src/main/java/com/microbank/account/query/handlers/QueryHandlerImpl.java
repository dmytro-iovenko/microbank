package com.microbank.account.query.handlers;

import java.util.List;

import com.microbank.account.query.entities.Account;
import com.microbank.account.query.queries.FindAccountByIdHolderQuery;
import com.microbank.account.query.queries.FindAccountByIdQuery;
import com.microbank.account.query.queries.FindAccountsWithBalanceQuery;
import com.microbank.account.query.queries.FindAllAccountsQuery;

public class QueryHandlerImpl implements QueryHandler {

    @Override
    public List<Account> handle(FindAllAccountsQuery query) {
        return null;
    }

    @Override
    public List<Account> handle(FindAccountByIdQuery query) {
        return null;
    }

    @Override
    public List<Account> handle(FindAccountByIdHolderQuery query) {
        return null;
    }

    @Override
    public List<Account> handle(FindAccountsWithBalanceQuery query) {
        return null;
    }
    
}
