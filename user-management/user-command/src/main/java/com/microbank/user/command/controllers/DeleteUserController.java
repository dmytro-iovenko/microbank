package com.microbank.user.command.controllers;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.base.core.dispatchers.CommandDispatcher;
import com.microbank.user.command.commands.DeleteUserCommand;
import com.microbank.user.command.dto.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/deleteUser")
@Slf4j
public class DeleteUserController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable(value = "id") String id) {
        try {
            commandDispatcher.send(new DeleteUserCommand(id));
            return new ResponseEntity<>(new BaseResponse("User was succesfully deleted!"), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormat
                    .format("Error while processing delete user request for id - {0}", id);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
