package com.microbank.user.core.models.events;

import lombok.Data;

@Data
public class UserDeletedEvent {
    private String id;
}
