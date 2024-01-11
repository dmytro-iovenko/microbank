package com.microbank.user.command.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microbank.user.command.aggregates.UserAggregate;
import com.microbank.base.core.aggregates.AggregateRoot;
import com.microbank.base.core.events.BaseEvent;
import com.microbank.base.core.services.EventSourcingHandler;
import com.microbank.base.core.services.EventStore;

@Service
public class UserEventSourcingHandler implements EventSourcingHandler<UserAggregate> {
    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommitedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommited();
    }

    @Override
    public UserAggregate getById(String id) {
        UserAggregate aggregate = new UserAggregate();
        List<BaseEvent> events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            Optional<Integer> latestVersion = events.stream().map(event -> event.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

}
