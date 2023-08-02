package com.microbank.account.command.controllers;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.account.command.commands.DepositFundsCommand;
import com.microbank.account.command.dispatchers.CommandDispatcher;
import com.microbank.account.core.dto.BaseResponse;

@RestController
@RequestMapping(path = "/api/v1/depositFunds")
public class DepositFundsController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id, @RequestBody DepositFundsCommand command) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Deposit funds request completed succesfully!"), HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormat
                    .format("Error while processing request to deposit funds to bank account with id - {0}", id);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
