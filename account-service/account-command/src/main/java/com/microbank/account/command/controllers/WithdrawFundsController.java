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

import com.microbank.account.command.commands.WithdrawFundsCommand;
import com.microbank.account.command.dispatchers.CommandDispatcher;
import com.microbank.account.core.dto.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/withdrawFunds")
@Slf4j
public class WithdrawFundsController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(value = "id") String id, @RequestBody WithdrawFundsCommand command) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Withdraw funds request completed succesfully!"), HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormat.format("Error while processing request to withdraw funds from bank account with id - {0}", id);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
}
