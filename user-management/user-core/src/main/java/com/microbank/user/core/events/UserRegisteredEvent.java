package com.microbank.user.core.events;

import com.microbank.user.core.models.User;
import com.microbank.base.core.events.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserRegisteredEvent extends BaseEvent {
    private String id;
    private User user;
}
