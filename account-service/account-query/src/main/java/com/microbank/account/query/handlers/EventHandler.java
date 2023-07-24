package com.microbank.account.query.handlers;

import com.microbank.account.core.events.AccountClosedEvent;
import com.microbank.account.core.events.AccountOpenedEvent;
import com.microbank.account.core.events.FundsDepositedEvent;
import com.microbank.account.core.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
