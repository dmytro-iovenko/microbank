package com.microbank.account.command.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microbank.account.command.aggregates.AccountAggregate;
import com.microbank.account.command.kafka.EventProducer;
import com.microbank.account.command.models.EventModel;
import com.microbank.account.command.repositories.EventStoreRepository;
import com.microbank.base.core.events.BaseEvent;

@Service
public class EventStoreImpl implements EventStore {
    @Autowired
    EventStoreRepository eventStoreRepository;
    @Autowired
    EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new RuntimeException();
        }
        int version = expectedVersion;
        for (BaseEvent event : events) {
            event.setVersion(++version);
            EventModel eventModel = EventModel.builder()
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            EventModel persistedEvent = eventStoreRepository.save(eventModel);
            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new IllegalStateException("Incorrect account ID provided!");
        }
        return eventStream.stream().map(event -> event.getEventData()).collect(Collectors.toList());
    }

}
