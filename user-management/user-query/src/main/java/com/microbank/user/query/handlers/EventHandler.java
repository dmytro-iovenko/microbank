package com.microbank.user.query.handlers;

import com.microbank.user.core.events.UserDeletedEvent;
import com.microbank.user.core.events.UserRegisteredEvent;
import com.microbank.user.core.events.UserUpdatedEvent;

public interface EventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserDeletedEvent event);
}
