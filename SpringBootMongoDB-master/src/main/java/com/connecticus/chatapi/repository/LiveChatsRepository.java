package com.connecticus.chatapi.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.connecticus.chatapi.bean.ResultResponse;
import com.connecticus.chatapi.entity.LiveChats;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public interface LiveChatsRepository extends MongoRepository<LiveChats, String> {

	//LiveChats findById(String instanceId);

	LiveChats findById(String chatId);




}
