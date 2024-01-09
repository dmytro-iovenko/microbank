package com.microbank.user.core.events;

import com.microbank.base.core.events.BaseEvent;
import com.microbank.user.core.models.User;

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
public class UserUpdatedEvent extends BaseEvent {
    private User user;
}
