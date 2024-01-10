package com.microbank.base.core.services;

import java.util.List;

import com.microbank.base.core.events.BaseEvent;

public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateId);
}
