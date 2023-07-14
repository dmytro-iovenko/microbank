package com.microbank.account.core.events;

import lombok.Data;

@Data
public class FundsWithdrawnEvent {
    private String id;
    private double amount;
}
