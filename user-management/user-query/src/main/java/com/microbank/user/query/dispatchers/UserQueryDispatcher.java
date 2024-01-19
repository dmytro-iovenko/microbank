package com.microbank.user.query.dispatchers;

import com.microbank.user.query.dto.UserLookupResponse;
import com.microbank.user.query.queries.BaseQuery;

public interface UserQueryDispatcher {
    @FunctionalInterface
    public interface QueryHandlerMethod<T extends BaseQuery> {
        UserLookupResponse handle(T query);
    }
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    UserLookupResponse send(BaseQuery query);

}
