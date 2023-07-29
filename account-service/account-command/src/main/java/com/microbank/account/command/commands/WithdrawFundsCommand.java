package com.microbank.account.command.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WithdrawFundsCommand extends BaseCommand {
    private double amount;
}
