package com.microbank.user.command.commands;

import com.microbank.user.core.models.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserCommand {
    private String id;
    private User user;
}
