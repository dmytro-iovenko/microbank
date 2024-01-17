package com.microbank.user.command.controllers;

import java.text.MessageFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.base.core.dispatchers.CommandDispatcher;
import com.microbank.user.command.commands.RegisterUserCommand;
import com.microbank.user.command.dto.RegisterUserResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/v1/registerUser")
@Slf4j
public class RegisterUserController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(
                    new RegisterUserResponse("Register user request completed succesfully!", id),
                    HttpStatus.CREATED);
        } catch(Exception e) {
            String safeErrorMessage = MessageFormat
                    .format("Error while processing register user request for id - {0}", id);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMessage, id),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
