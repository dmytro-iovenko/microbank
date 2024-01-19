package com.microbank.user.query.handlers;

import org.springframework.stereotype.Service;

import com.microbank.user.query.dto.UserLookupResponse;
import com.microbank.user.query.queries.FindAllUsersQuery;
import com.microbank.user.query.queries.FindUserByIdQuery;
import com.microbank.user.query.queries.SearchUsersQuery;

@Service
public interface UserQueryHandler {
    UserLookupResponse handle(FindAllUsersQuery query);
    UserLookupResponse handle(FindUserByIdQuery query);
    UserLookupResponse handle(SearchUsersQuery query);
}
