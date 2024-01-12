package com.microbank.user.command.services;

import com.microbank.user.command.commands.DeleteUserCommand;
import com.microbank.user.command.commands.RegisterUserCommand;
import com.microbank.user.command.commands.UpdateUserCommand;

public interface UserCommandHandler {
    void handle(RegisterUserCommand command);
    void handle(UpdateUserCommand command);
    void handle(DeleteUserCommand command);
}
