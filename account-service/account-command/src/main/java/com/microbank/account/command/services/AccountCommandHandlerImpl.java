package com.microbank.account.command.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microbank.account.command.aggregates.AccountAggregate;
import com.microbank.account.command.commands.CloseAccountCommand;
import com.microbank.account.command.commands.DepositFundsCommand;
import com.microbank.account.command.commands.OpenAccountCommand;
import com.microbank.account.command.commands.WithdrawFundsCommand;
import com.microbank.base.core.services.EventSourcingHandler;

@Service
public class AccountCommandHandlerImpl implements AccountCommandHandler {
    @Autowired
    private EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    @Override
    public void handle(OpenAccountCommand command) {
        AccountAggregate accountAggregate = new AccountAggregate(command);
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(DepositFundsCommand command) {
        AccountAggregate accountAggregate = eventSourcingHandler.getById(command.getId());
        accountAggregate.depositFunds(command.getAmount());
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(WithdrawFundsCommand command) {
        AccountAggregate accountAggregate = eventSourcingHandler.getById(command.getId());
        if (command.getAmount() > accountAggregate.getBalance()) {
            throw new IllegalStateException("Withdrwal denied, insufficient funds!");
        }
        accountAggregate.withdrawFunds(command.getAmount());
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(CloseAccountCommand command) {
        AccountAggregate accountAggregate = eventSourcingHandler.getById(command.getId());
        accountAggregate.closeAccount();
        eventSourcingHandler.save(accountAggregate);
    }

}
