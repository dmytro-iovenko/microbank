package com.microbank.user.query.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microbank.user.core.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}
