package com.microbank.user.core.events;

import com.microbank.base.core.events.BaseEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@SuperBuilder
public class UserDeletedEvent extends BaseEvent {

}
