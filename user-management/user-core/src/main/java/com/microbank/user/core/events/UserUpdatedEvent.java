package com.microbank.user.core.events;

import com.microbank.user.core.models.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {
    private String id;
    private User user;
}
