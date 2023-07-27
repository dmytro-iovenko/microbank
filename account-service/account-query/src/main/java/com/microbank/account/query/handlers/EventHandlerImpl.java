package com.microbank.account.query.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microbank.account.core.events.AccountClosedEvent;
import com.microbank.account.core.events.AccountOpenedEvent;
import com.microbank.account.core.events.FundsDepositedEvent;
import com.microbank.account.core.events.FundsWithdrawnEvent;
import com.microbank.account.query.entities.Account;
import com.microbank.account.query.repositories.AccountRepository;

@Service
public class EventHandlerImpl implements EventHandler {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        Account account = Account.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreateDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();

        accountRepository.save(account);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void on(AccountClosedEvent event) {
        // TODO Auto-generated method stub

    }

}
