package com.microbank.account.command.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id) {
        super(id);
    }
}
