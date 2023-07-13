package com.microbank.account.command.commands;

import com.microbank.account.core.models.AccountType;

import lombok.Data;

@Data
public class OpenAccountCommand {
    private String id;
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
