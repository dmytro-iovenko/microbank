package com.microbank.user.command.commands;

import com.microbank.base.core.commands.BaseCommand;
import com.microbank.user.core.models.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UpdateUserCommand extends BaseCommand {
    private User user;
}
