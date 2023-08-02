package com.microbank.account.command.controllers;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.account.command.commands.CloseAccountCommand;
import com.microbank.account.command.dispatchers.CommandDispatcher;
import com.microbank.account.core.dto.BaseResponse;

@RestController
@RequestMapping(path = "/api/v1/closeAccount")
public class CloseAccountController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable(value = "id") String id) {
        try {
            commandDispatcher.send(new CloseAccountCommand(id));
            return new ResponseEntity<>(new BaseResponse("Bank account closure request completed succesfully!"), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormat
                    .format("Error while processing request to close bank account with id - {0}", id);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
