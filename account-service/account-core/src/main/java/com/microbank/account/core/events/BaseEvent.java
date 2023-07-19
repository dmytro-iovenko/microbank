package com.microbank.account.core.events;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class BaseEvent {
    private String id;
    private int version;
}
