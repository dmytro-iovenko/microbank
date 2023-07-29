package com.microbank.account.command.commands;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class BaseCommand {
    private String id;
}
