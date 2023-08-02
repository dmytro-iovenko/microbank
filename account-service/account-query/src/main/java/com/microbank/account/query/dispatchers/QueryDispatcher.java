package com.microbank.account.query.dispatchers;

import java.util.List;

import com.microbank.account.query.entities.Account;
import com.microbank.account.query.queries.BaseQuery;

public interface QueryDispatcher {
    @FunctionalInterface
    public interface QueryHandlerMethod<T extends BaseQuery> {
        void handle(T command);
    }
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    List<Account> send(BaseQuery query);

}
