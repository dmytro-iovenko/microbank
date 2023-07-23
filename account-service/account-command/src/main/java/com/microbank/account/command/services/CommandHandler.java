package com.microbank.account.command.services;

import com.microbank.account.command.commands.CloseAccountCommand;
import com.microbank.account.command.commands.DepositFundsCommand;
import com.microbank.account.command.commands.OpenAccountCommand;
import com.microbank.account.command.commands.WithdrawFundsCommand;

public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(DepositFundsCommand command);
    void handle(WithdrawFundsCommand command);
    void handle(CloseAccountCommand command);
}
