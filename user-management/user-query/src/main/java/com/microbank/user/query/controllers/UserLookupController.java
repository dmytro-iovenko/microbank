package com.microbank.user.query.controllers;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.user.core.models.User;
import com.microbank.user.query.dispatchers.UserQueryDispatcher;
import com.microbank.user.query.dto.UserLookupResponse;
import com.microbank.user.query.queries.FindAllUsersQuery;

@RestController
@RequestMapping(path = "/api/v1/userLookup")
public class UserLookupController {
    @Autowired
    private UserQueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<UserLookupResponse> getAllUsers() {
        try {
            List<User> users = queryDispatcher.send(new FindAllUsersQuery()).getUsers();
            if (users == null || users.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            UserLookupResponse response = UserLookupResponse.builder()
                    .users(users)
                    .message(MessageFormat.format("Succesfully returned {0} user(s)", users.size()))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get all users request!";
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
