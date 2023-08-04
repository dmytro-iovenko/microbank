package com.microbank.account.query.controllers;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.account.query.dispatchers.QueryDispatcher;
import com.microbank.account.query.dto.AccountLookupResponse;
import com.microbank.account.query.dto.EqualityType;
import com.microbank.account.query.entities.Account;
import com.microbank.account.query.queries.FindAccountByIdHolderQuery;
import com.microbank.account.query.queries.FindAccountByIdQuery;
import com.microbank.account.query.queries.FindAccountsWithBalanceQuery;
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

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id") String id) {
        try {
            List<Account> accounts = queryDispatcher.send(new FindAccountByIdQuery(id));
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            AccountLookupResponse response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Succesfully returned bank account")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get account by ID request!";
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byHolder/{accountHolder}")
    public ResponseEntity<AccountLookupResponse> getAccountByHolder(
            @PathVariable(value = "accountHolder") String accountHolder) {
        try {
            List<Account> accounts = queryDispatcher.send(new FindAccountByIdHolderQuery(accountHolder));
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            AccountLookupResponse response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message("Succesfully returned bank account")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get account by holder request!";
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/withBalance/{equalityType}/{balance}")
    public ResponseEntity<AccountLookupResponse> getAccountsWithBalance(
            @PathVariable(value = "equalityType") EqualityType equalityType,
            @PathVariable(value = "balance") double balance) {
        try {
            List<Account> accounts = queryDispatcher.send(new FindAccountsWithBalanceQuery(equalityType, balance));
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            AccountLookupResponse response = AccountLookupResponse.builder()
                    .accounts(accounts)
                    .message(MessageFormat.format("Succesfully returned {0} bank account(s)", accounts.size()))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get accounts with balance request!";
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
