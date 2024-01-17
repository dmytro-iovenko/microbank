package com.microbank.user.command.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microbank.base.core.events.BaseEvent;

@Service
public class UserEventProducerImpl implements UserEventProducer {
    @Autowired
    private KafkaTemplate<String, BaseEvent> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic, event);
    }
    
}
