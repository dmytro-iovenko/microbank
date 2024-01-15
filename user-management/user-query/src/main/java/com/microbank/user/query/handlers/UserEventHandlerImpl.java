package com.microbank.user.query.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microbank.user.core.events.UserDeletedEvent;
import com.microbank.user.core.events.UserRegisteredEvent;
import com.microbank.user.core.events.UserUpdatedEvent;
import com.microbank.user.query.repositories.UserRepository;

@Service
public class UserEventHandlerImpl implements UserEventHandler {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @Override
    public void on(UserDeletedEvent event) {
        userRepository.deleteById(event.getId());
    }
    
}
