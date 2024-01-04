package com.microbank.account.command.kafka;

import com.microbank.base.core.events.BaseEvent;

public interface EventProducer {
        void produce(String topic, BaseEvent event);
}
