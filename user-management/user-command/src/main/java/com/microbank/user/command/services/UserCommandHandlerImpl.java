package com.microbank.user.command.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.microbank.base.core.services.EventSourcingHandler;
import com.microbank.user.command.aggregates.UserAggregate;
import com.microbank.user.command.commands.DeleteUserCommand;
import com.microbank.user.command.commands.RegisterUserCommand;
import com.microbank.user.command.commands.UpdateUserCommand;

public class UserCommandHandlerImpl implements UserCommandHandler {
    @Autowired
    private EventSourcingHandler<UserAggregate> eventSourcingHandler;

    @Override
    public void handle(RegisterUserCommand command) {
        UserAggregate userAggregate = new UserAggregate(command);
        eventSourcingHandler.save(userAggregate);
    }

    @Override
    public void handle(UpdateUserCommand command) {
        UserAggregate userAggregate = eventSourcingHandler.getById(command.getId());
        userAggregate.updateUser(command);
        eventSourcingHandler.save(userAggregate);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        UserAggregate userAggregate = eventSourcingHandler.getById(command.getId());
        userAggregate.deleteUser();
        eventSourcingHandler.save(userAggregate);
        
    }
    
}
