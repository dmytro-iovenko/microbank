package com.microbank.account.command.aggregates;

import java.util.Date;

import com.microbank.account.command.commands.OpenAccountCommand;
import com.microbank.account.core.aggregates.AggregateRoot;
import com.microbank.account.core.events.AccountOpenedEvent;
import com.microbank.account.core.events.FundsDepositedEvent;
import com.microbank.account.core.events.FundsWithdrawnEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createDate(new Date())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.setId(event.getId());
        this.setActive(true);
        this.setBalance(event.getOpeningBalance());
    }

    public void depositFunds(double amount) {
        // TODO: to add validation for amount <= 0
        raiseEvent(FundsDepositedEvent.builder()
                .id(this.getId())
                .amount(amount)
                .build());
    }

    public void apply(FundsDepositedEvent event) {
        this.setId(event.getId());
        this.setBalance(this.getBalance() + event.getAmount());
    }

    public void withdrawFunds(double amount) {
        // TODO: to add validation for amount <= 0
        raiseEvent(FundsWithdrawnEvent.builder()
                .id(this.getId())
                .amount(amount)
                .build());
    }

    public void apply(FundsWithdrawnEvent event) {
        this.setId(event.getId());
        this.setBalance(this.getBalance() - event.getAmount());
    }

}
