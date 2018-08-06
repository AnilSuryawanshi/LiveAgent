package com.connecticus.chatapi.daoimpl;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.connecticus.chatapi.bean.JSONResponse;
import com.connecticus.chatapi.entity.LiveChatTrigger;

public interface LiveChatTriggerRepository extends MongoRepository<LiveChatTrigger, String> {

	//JSONResponse save(JSONResponse jsonResponse);

}
