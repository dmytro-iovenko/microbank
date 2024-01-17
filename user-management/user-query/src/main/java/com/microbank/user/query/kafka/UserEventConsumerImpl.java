package com.microbank.user.query.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.microbank.user.core.events.UserDeletedEvent;
import com.microbank.user.core.events.UserRegisteredEvent;
import com.microbank.user.core.events.UserUpdatedEvent;
import com.microbank.user.query.handlers.UserEventHandler;

@Service
public class UserEventConsumerImpl implements UserEventConsumer {
    @Autowired
    private UserEventHandler eventHandler;

    @KafkaListener(topics = "UserRegisteredEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(UserRegisteredEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "UserUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(UserUpdatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "UserDeletedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(UserDeletedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

}
