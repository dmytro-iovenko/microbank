package com.microbank.base.core.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.microbank.base.core.events.BaseEvent;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id
    private String id;
    private Date timestamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;
    private BaseEvent eventData;
    
}
