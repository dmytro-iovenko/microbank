package com.microbank.user.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microbank.base.core.dispatchers.CommandDispatcher;
import com.microbank.user.command.commands.DeleteUserCommand;
import com.microbank.user.command.commands.RegisterUserCommand;
import com.microbank.user.command.commands.UpdateUserCommand;
import com.microbank.user.command.services.UserCommandHandler;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UserCommandApplication {
	@Autowired
	private CommandDispatcher commandDispatcher;
	@Autowired
	private UserCommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(UserCommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandler() {
		commandDispatcher.registerHandler(RegisterUserCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(UpdateUserCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DeleteUserCommand.class, commandHandler::handle);
	}

}
