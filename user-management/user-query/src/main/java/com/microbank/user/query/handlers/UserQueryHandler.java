package com.microbank.user.query.handlers;

import com.microbank.user.query.dto.UserLookupResponse;
import com.microbank.user.query.queries.FindAllUsersQuery;
import com.microbank.user.query.queries.FindUserByIdQuery;
import com.microbank.user.query.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponse handle(FindAllUsersQuery query);
    UserLookupResponse handle(FindUserByIdQuery query);
    UserLookupResponse handle(SearchUsersQuery query);
}
