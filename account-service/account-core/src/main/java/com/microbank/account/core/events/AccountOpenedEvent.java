package com.microbank.account.core.events;

import java.util.Date;

import com.microbank.account.core.models.AccountType;

import lombok.Data;

@Data
public class AccountOpenedEvent {
    private String id;
    private String accountHolder;
    private AccountType accountType;
    private Date createDate;
    private double openingBalance;
}
