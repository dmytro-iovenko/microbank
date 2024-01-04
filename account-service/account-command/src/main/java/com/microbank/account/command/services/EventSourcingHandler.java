package com.microbank.account.command.services;

import com.microbank.account.command.aggregates.AccountAggregate;
import com.microbank.base.core.aggregates.AggregateRoot;

public interface EventSourcingHandler {
    void save(AggregateRoot aggregate);
    AccountAggregate getById(String id);
}
