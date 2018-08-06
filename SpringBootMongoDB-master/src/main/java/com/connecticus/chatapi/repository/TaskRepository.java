package com.connecticus.chatapi.repository;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.connecticus.chatapi.entity.User;


public interface TaskRepository extends MongoRepository<User, String> {

	

}
