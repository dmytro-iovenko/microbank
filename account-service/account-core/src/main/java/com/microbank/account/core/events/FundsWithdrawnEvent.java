package com.microbank.account.core.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class FundsWithdrawnEvent extends BaseEvent {
    private double amount;
}
