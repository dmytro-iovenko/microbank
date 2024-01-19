package com.microbank.user.query.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.microbank.user.core.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'$or' : [{'firstname': {$regex : ?0, $options: '1'}}, {'lastname': {$regex : ?0, $options: '1'}}, {'emailAddress': {$regex : ?0, $options: '1'}}, {'account.username': {$regex : ?0, $options: '1'}}]}")
    List<User> findByFilterRegex(String filter);
}
