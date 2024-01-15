package com.microbank.account.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.microbank.account.query.dispatchers.QueryDispatcher;
import com.microbank.account.query.handlers.AccountQueryHandler;
import com.microbank.account.query.queries.FindAccountByIdHolderQuery;
import com.microbank.account.query.queries.FindAccountByIdQuery;
import com.microbank.account.query.queries.FindAccountsWithBalanceQuery;
import com.microbank.account.query.queries.FindAllAccountsQuery;

import jakarta.annotation.PostConstruct;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountQueryApplication {
	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private AccountQueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(AccountQueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountsWithBalanceQuery.class, queryHandler::handle);
	}

}
