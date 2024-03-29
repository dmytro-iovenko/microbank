package com.microbank.account.command.aggregates;

import java.util.Date;

import org.apache.kafka.common.errors.IllegalSaslStateException;

import com.microbank.account.command.commands.OpenAccountCommand;
import com.microbank.account.core.events.AccountClosedEvent;
import com.microbank.account.core.events.AccountOpenedEvent;
import com.microbank.account.core.events.FundsDepositedEvent;
import com.microbank.account.core.events.FundsWithdrawnEvent;
import com.microbank.base.core.aggregates.AggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
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
        if (!this.getActive()) {
            throw new IllegalSaslStateException("Funds cannot be deposited into a closed account!");
        }
        if (amount <= 0) {
            throw new IllegalSaslStateException("The deposit amount should be greater then 0!");
        }
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
        if (!this.getActive()) {
            throw new IllegalSaslStateException("Funds cannot be withdrawn from a closed account!");
        }
        if (amount <= 0) {
            throw new IllegalSaslStateException("The withdraw amount should be greater then 0!");
        }
        raiseEvent(FundsWithdrawnEvent.builder()
                .id(this.getId())
                .amount(amount)
                .build());
    }

    public void apply(FundsWithdrawnEvent event) {
        this.setId(event.getId());
        this.setBalance(this.getBalance() - event.getAmount());
    }

    public void closeAccount() {
        if (!this.getActive()) {
            throw new IllegalSaslStateException("The bank account has already been closed!");
        }
        raiseEvent(AccountClosedEvent.builder()
                .id(this.getId())
                .build());
    }

    public void apply(AccountClosedEvent event) {
        this.setId(event.getId());
        this.setActive(false);
    }

}
