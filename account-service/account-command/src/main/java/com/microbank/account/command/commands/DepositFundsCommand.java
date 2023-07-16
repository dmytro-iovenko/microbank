package com.microbank.account.command.commands;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class DepositFundsCommand extends BaseCommand {
    private double amount;
}
