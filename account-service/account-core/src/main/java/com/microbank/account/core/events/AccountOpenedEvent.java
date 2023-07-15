package com.microbank.account.core.events;

import java.util.Date;

import com.microbank.account.core.models.AccountType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
    private String accountHolder;
    private AccountType accountType;
    private Date createDate;
    private double openingBalance;
}
