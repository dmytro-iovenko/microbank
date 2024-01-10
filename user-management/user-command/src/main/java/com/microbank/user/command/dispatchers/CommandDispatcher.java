package com.microbank.user.command.dispatchers;

import com.microbank.base.core.commands.BaseCommand;

public interface CommandDispatcher {
    @FunctionalInterface
    public interface CommandHandlerMethod<T extends BaseCommand> {
        void handle(T command);
    }
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
