package com.microbank.user.query.repositories;

import org.springframework.data.repository.CrudRepository;

import com.microbank.user.core.models.User;

public interface UserRepository extends CrudRepository<User, String> {
}
