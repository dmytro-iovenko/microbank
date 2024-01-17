package com.microbank.user.command.kafka;

import com.microbank.base.core.events.BaseEvent;

public interface UserEventProducer {
        void produce(String topic, BaseEvent event);
}
