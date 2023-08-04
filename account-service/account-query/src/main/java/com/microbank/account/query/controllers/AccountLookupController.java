package com.microbank.account.query.controllers;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.account.query.dispatchers.QueryDispatcher;
import com.microbank.account.query.dto.AccountLookupResponse;
import com.microbank.account.query.entities.Account;
import com.microbank.account.query.queries.FindAllAccountsQuery;

@RestController
@RequestMapping(path = "/api/v1/accountLookup")
public class AccountLookupController {
        @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {
        try {
            List<Account> accounts = queryDispatcher.send(new FindAllAccountsQuery());
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            AccountLookupResponse response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Succesfully returned {0} bank account(s)", accounts.size()))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get all accounts request!";
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
