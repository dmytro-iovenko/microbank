package com.microbank.account.command.commands;

import lombok.Data;

@Data
public class WithdrawFundsCommand {
    private String id;
    private double amount;
}
