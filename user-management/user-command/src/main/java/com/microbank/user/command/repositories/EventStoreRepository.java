package com.microbank.user.command.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microbank.base.core.models.EventModel;

@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, String> {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
