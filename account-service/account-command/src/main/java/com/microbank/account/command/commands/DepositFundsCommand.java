package com.microbank.account.command.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DepositFundsCommand extends BaseCommand {
    private double amount;
}
