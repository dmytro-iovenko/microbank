package com.microbank.account.command.commands;


import lombok.Data;

@Data
public class DepositFundsCommand {
    private String id;
    private double amount;
}
