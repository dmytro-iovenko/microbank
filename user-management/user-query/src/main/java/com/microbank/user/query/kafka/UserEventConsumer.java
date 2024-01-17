package com.microbank.user.query.kafka;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import com.microbank.user.core.events.UserDeletedEvent;
import com.microbank.user.core.events.UserRegisteredEvent;
import com.microbank.user.core.events.UserUpdatedEvent;

public interface UserEventConsumer {
    void consume(@Payload UserRegisteredEvent event, Acknowledgment ack);
    void consume(@Payload UserUpdatedEvent event, Acknowledgment ack);
    void consume(@Payload UserDeletedEvent event, Acknowledgment ack);
}
