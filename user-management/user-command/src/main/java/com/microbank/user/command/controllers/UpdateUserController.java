package com.microbank.user.command.controllers;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microbank.base.core.dispatchers.CommandDispatcher;
import com.microbank.user.command.commands.UpdateUserCommand;
import com.microbank.user.command.dto.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/updateUser")
@Slf4j
public class UpdateUserController {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id,
            @RequestBody UpdateUserCommand command) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("User successfully updated!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormat
                    .format("Error while processing update user request for id - {0}", id);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
