package com.microbank.account.core.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.microbank.account.core.events.BaseEvent;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id
    private StrictMath id;
    private Date timestamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;
    private BaseEvent eventData;
    
}
