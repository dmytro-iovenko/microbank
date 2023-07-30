package com.microbank.account.command.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microbank.account.core.events.BaseEvent;

@Service
public class EventProducerImpl implements EventProducer {
    @Autowired
    private KafkaTemplate<String, BaseEvent> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic, event);
    }
    
}
