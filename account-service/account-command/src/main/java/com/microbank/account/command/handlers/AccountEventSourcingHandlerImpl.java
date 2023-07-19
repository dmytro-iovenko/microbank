package com.microbank.account.command.handlers;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.microbank.account.command.aggregates.AccountAggregate;
import com.microbank.account.command.services.AccountEventStore;
import com.microbank.account.core.aggregates.AggregateRoot;
import com.microbank.account.core.events.BaseEvent;

public class AccountEventSourcingHandlerImpl implements AccountEventSourcingHandler {
        @Autowired
    private AccountEventStore accountEventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        accountEventStore.saveEvents(aggregate.getId(), aggregate.getUncommitedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommited();
    }

    @Override
    public AccountAggregate getById(String id) {
        AccountAggregate aggregate = new AccountAggregate();
        List<BaseEvent> events = accountEventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            Optional<Integer> latestVersion = events.stream().map(event -> event.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

}
