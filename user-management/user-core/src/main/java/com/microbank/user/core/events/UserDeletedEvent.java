package com.microbank.user.core.events;

import lombok.Data;

@Data
public class UserDeletedEvent {
    private String id;
}
