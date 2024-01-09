package com.microbank.user.command.aggregates;

import java.util.UUID;

import com.microbank.base.core.aggregates.AggregateRoot;
import com.microbank.user.command.commands.RegisterUserCommand;
import com.microbank.user.command.commands.UpdateUserCommand;
import com.microbank.user.command.security.PasswordEncoder;
import com.microbank.user.command.security.PasswordEncoderImpl;
import com.microbank.user.core.events.UserDeletedEvent;
import com.microbank.user.core.events.UserRegisteredEvent;
import com.microbank.user.core.events.UserUpdatedEvent;
import com.microbank.user.core.models.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAggregate extends AggregateRoot {
    private User user;
    private Boolean active;
    
    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    public UserAggregate(RegisterUserCommand command) {
        User newUser = command.getUser();
        newUser.setId(command.getId());

        String password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        String encryptedPassword = passwordEncoder.encryptPassword(password);
        newUser.getAccount().setPassword(encryptedPassword);

        raiseEvent(UserRegisteredEvent.builder()
            .id(command.getId())
            .user(command.getUser())
            .build()
        );
    }

    public void apply(UserRegisteredEvent event) {
        this.setId(event.getId());
        this.setUser(event.getUser());
        this.setActive(true);
    }

    public void updateUser(UpdateUserCommand command) {
        User updatedUser = command.getUser();
        updatedUser.setId(command.getId());

        String password = updatedUser.getAccount().getPassword();
        String encryptedPassword = passwordEncoder.encryptPassword(password);
        updatedUser.getAccount().setPassword(encryptedPassword);

        raiseEvent(UserUpdatedEvent.builder()
            .id(UUID.randomUUID().toString())
            .user(command.getUser())
            .build());
    }

    public void apply(UserUpdatedEvent event) {
        this.setUser(event.getUser());
    }

    public void deleteUser() {
        // TODO: to add validation if user is active or not
        raiseEvent(UserDeletedEvent.builder()
                .id(this.getId())
                .build());
    }

    public void apply(UserDeletedEvent event) {
        this.setId(event.getId());
        this.setActive(false);
    }
}
