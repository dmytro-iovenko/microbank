package com.microbank.user.command.commands;

import com.microbank.base.core.commands.BaseCommand;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DeleteUserCommand extends BaseCommand {
    public DeleteUserCommand(String id) {
        super(id);
    }
}