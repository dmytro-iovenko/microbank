package com.microbank.user.query.dispatchers;

import java.util.List;

import com.microbank.user.core.models.User;
import com.microbank.user.query.queries.BaseQuery;

public interface UserQueryDispatcher {
    @FunctionalInterface
    public interface QueryHandlerMethod<T extends BaseQuery> {
        List<User> handle(T query);
    }
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    List<User> send(BaseQuery query);

}
