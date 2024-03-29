package com.microbank.account.command.controllers;

import java.text.MessageFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.account.command.commands.OpenAccountCommand;
import com.microbank.account.command.dto.OpenAccountResponse;
import com.microbank.account.core.dto.BaseResponse;
import com.microbank.base.core.dispatchers.CommandDispatcher;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/openAccount")
@Slf4j
public class OpenAccountController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(
                    new OpenAccountResponse("Bank account creation request completed succesfully!", id),
                    HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            log.warn("Client made a bad request - {}", e.getMessage());
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormat
                    .format("Error while processing request to open a new bank account for id - {0}", id);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new OpenAccountResponse(safeErrorMessage, id),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
