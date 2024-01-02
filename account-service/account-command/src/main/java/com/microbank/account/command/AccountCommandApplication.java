package com.microbank.account.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.microbank.account.command.commands.CloseAccountCommand;
import com.microbank.account.command.commands.DepositFundsCommand;
import com.microbank.account.command.commands.OpenAccountCommand;
import com.microbank.account.command.commands.WithdrawFundsCommand;
import com.microbank.account.command.dispatchers.CommandDispatcher;
import com.microbank.account.command.services.CommandHandler;

import jakarta.annotation.PostConstruct;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountCommandApplication {
	@Autowired
	private CommandDispatcher commandDispatcher;
	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(AccountCommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandler() {
		commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
	}

}
