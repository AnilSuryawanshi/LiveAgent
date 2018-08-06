package com.connecticus.chatapi.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.connecticus.chatapi.entity.AgentMessage;


public interface AgentMessageRepository extends MongoRepository<AgentMessage, String> {


}
