package com.microbank.account.query.handlers;

import org.springframework.stereotype.Service;

import com.microbank.account.core.events.AccountClosedEvent;
import com.microbank.account.core.events.AccountOpenedEvent;
import com.microbank.account.core.events.FundsDepositedEvent;
import com.microbank.account.core.events.FundsWithdrawnEvent;

@Service
public class EventHandlerImpl implements EventHandler {

    @Override
    public void on(AccountOpenedEvent event) {
        // TODO Auto-generated method stub
        
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
