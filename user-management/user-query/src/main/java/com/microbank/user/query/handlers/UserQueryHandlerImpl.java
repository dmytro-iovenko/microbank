package com.microbank.user.query.handlers;

import org.springframework.stereotype.Service;

import com.microbank.user.query.dto.UserLookupResponse;
import com.microbank.user.query.queries.FindAllUsersQuery;
import com.microbank.user.query.queries.FindUserByIdQuery;
import com.microbank.user.query.queries.SearchUsersQuery;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {

    @Override
    public UserLookupResponse handle(FindAllUsersQuery query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserLookupResponse handle(FindUserByIdQuery query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserLookupResponse handle(SearchUsersQuery query) {
        // TODO Auto-generated method stub
        return null;
    }

}
