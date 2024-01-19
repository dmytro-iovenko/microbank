package com.microbank.user.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microbank.user.query.dispatchers.UserQueryDispatcher;
import com.microbank.user.query.handlers.UserQueryHandler;
import com.microbank.user.query.queries.FindAllUsersQuery;
import com.microbank.user.query.queries.FindUserByIdQuery;
import com.microbank.user.query.queries.SearchUsersQuery;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UserQueryApplication {
	@Autowired
	private UserQueryDispatcher queryDispatcher;

	@Autowired
	private UserQueryHandler queryHandler;
	
	public static void main(String[] args) {
		SpringApplication.run(UserQueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllUsersQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindUserByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(SearchUsersQuery.class, queryHandler::handle);
	}
}
