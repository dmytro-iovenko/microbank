package com.microbank.account.core.events;

import lombok.Data;

@Data
public class FundsDepositedEvent {
    private String id;
    private double amount;
}
