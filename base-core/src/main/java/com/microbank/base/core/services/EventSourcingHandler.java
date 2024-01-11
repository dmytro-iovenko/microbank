package com.microbank.base.core.services;

import com.microbank.base.core.aggregates.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);
}
