package com.microbank.account.command.services;

import java.util.List;

import com.microbank.account.core.events.BaseEvent;

public interface AccountEventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateId);
}
