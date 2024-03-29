package com.microbank.account.command.commands;

import com.microbank.account.core.models.AccountType;
import com.microbank.base.core.commands.BaseCommand;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
