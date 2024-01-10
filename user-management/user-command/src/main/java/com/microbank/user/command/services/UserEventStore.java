package com.microbank.user.command.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microbank.user.command.aggregates.UserAggregate;
import com.microbank.user.command.repositories.EventStoreRepository;
import com.microbank.base.core.events.BaseEvent;
import com.microbank.base.core.models.EventModel;
import com.microbank.base.core.services.EventStore;

@Service
public class UserEventStore implements EventStore {
    @Autowired
    EventStoreRepository eventStoreRepository;

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
                    .aggregateType(UserAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            EventModel persistedEvent = eventStoreRepository.save(eventModel);
            if (persistedEvent != null) {
                // TODO: produce event to Kafka
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