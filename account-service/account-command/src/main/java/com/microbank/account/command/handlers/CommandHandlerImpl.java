package com.microbank.account.command.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.microbank.account.command.aggregates.AccountAggregate;
import com.microbank.account.command.commands.CloseAccountCommand;
import com.microbank.account.command.commands.DepositFundsCommand;
import com.microbank.account.command.commands.OpenAccountCommand;
import com.microbank.account.command.commands.WithdrawFundsCommand;

public class CommandHandlerImpl implements CommandHandler {
    @Autowired
    private EventSourcingHandler eventSourcingHandler;

    @Override
    public void handle(OpenAccountCommand command) {
        AccountAggregate accountAggregate = new AccountAggregate(command);
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(DepositFundsCommand command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle(WithdrawFundsCommand command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle(CloseAccountCommand command) {
        // TODO Auto-generated method stub
        
    }


}
