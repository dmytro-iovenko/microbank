package com.microbank.account.core.aggregates;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.microbank.account.core.events.BaseEvent;

import lombok.Data;

@Data
public abstract class AggregateRoot {
    private String id;
    private final List<BaseEvent> changes = new ArrayList<>(); 
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    protected void applyChange(BaseEvent event, Boolean isNewEvent) {
        try {
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            logger.log(Level.WARNING, MessageFormat.format("The apply method was not found in the aggregate for {0}",
                    event.getClass().getName()), e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error applying event to aggregate", e);
        } finally {
            if (isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

}
