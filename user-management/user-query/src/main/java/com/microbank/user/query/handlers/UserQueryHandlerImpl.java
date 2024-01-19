package com.microbank.user.query.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microbank.user.core.models.Account;
import com.microbank.user.core.models.User;
import com.microbank.user.query.dto.UserLookupResponse;
import com.microbank.user.query.queries.FindAllUsersQuery;
import com.microbank.user.query.queries.FindUserByIdQuery;
import com.microbank.user.query.queries.SearchUsersQuery;
import com.microbank.user.query.repositories.UserRepository;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserLookupResponse handle(FindAllUsersQuery query) {
        var users = userRepository.findAll();
        return new UserLookupResponse(users);
    }

    @Override
    public UserLookupResponse handle(FindUserByIdQuery query) {
        var user = userRepository.findById(query.getId());
        if (user.isEmpty()) return null;
        List<User> users = new ArrayList<>();
        users.add(user.get());
        return new UserLookupResponse(users);
    }

    @Override
    public UserLookupResponse handle(SearchUsersQuery query) {
        // TODO Auto-generated method stub
        return null;
    }

}
