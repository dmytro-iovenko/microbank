package com.microbank.user.aggregates;

import com.microbank.base.core.aggregates.AggregateRoot;
import com.microbank.user.command.commands.RegisterUserCommand;
import com.microbank.user.core.models.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserAggregate extends AggregateRoot {
    private User user;

    public UserAggregate(RegisterUserCommand command) {
        
    }
}
