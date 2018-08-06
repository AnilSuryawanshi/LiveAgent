package com.connecticus.chatapi.daoimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.connecticus.chatapi.bean.LiveChatDto;
import com.connecticus.chatapi.dao.ChatDao;
import com.connecticus.chatapi.entity.AgentMessage;
import com.connecticus.chatapi.entity.LiveChatTrigger;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.LiveChatsMessage;
import com.connecticus.chatapi.repository.AgentMessageRepository;
import com.connecticus.chatapi.repository.LiveChatsRepository;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class ChatDaoImpl implements ChatDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	LiveChatTriggerRepository liveChatTriggerRepository;

	@Autowired
	AgentMessageRepository agentMessageRepository;
	
	@Autowired
	LiveChatsRepository liveChatsRepository;

	@Override
	public LiveChatDto readChatByChatId(String instanceID) {

		LiveChats liveChats = new LiveChats();
		LiveChatDto liveChatDto=new LiveChatDto();
		Query query = new Query();
		List<LiveChatsMessage> liveChatsMessage=null;

		query.addCriteria(Criteria.where("instanceID").is(instanceID));

		liveChats = mongoTemplate.findOne(query, LiveChats.class);
		
		
		LiveChatsMessage e = liveChats.getLiveChatsMessage().get(liveChats.getLiveChatsMessage().size() - 1);
		
		liveChatDto.setUserMessage(e.getUserMessage());
		liveChatDto.setLiveAgentMessage(e.getLiveAgentMessage());
		liveChatDto.setCurrentTime(e.getCurrentTime());
		
		return liveChatDto;
	
	}

	public List<LiveChats> readByInstanceId(String instanceID) {

		List<LiveChats> liveChats = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("instanceID").is(instanceID));

		liveChats = mongoTemplate.find(query, LiveChats.class);
		return liveChats;
	}

	@Override
	public List<LiveChats> readLatestChat(String instanceID) {

		List<LiveChats> liveChats = null;
		
	//	String msgRead="1";
		Query query = new Query();
		query.addCriteria(Criteria.where("instanceID").is(instanceID));

		liveChats = mongoTemplate.find(query, LiveChats.class);
		
		
			return liveChats;
		
}
	
	@Override
	public LiveChatTrigger liveAgentTrigger(String handoverDecision, String sessionId, String user) {

		LiveChatTrigger liveChatTrigger = new LiveChatTrigger();

		liveChatTrigger.setHandoverDecision(handoverDecision);
		liveChatTrigger.setUser(user);
		liveChatTrigger.setSessionId(sessionId);

		liveChatTrigger = liveChatTriggerRepository.save(liveChatTrigger);

		return liveChatTrigger;
	}

	@Override
	public List<LiveChats> readLatestAgentChat(String instanceId) {
		List<LiveChats> liveChats = null;
		
		Query query = new Query();
		query.addCriteria(Criteria.where("instanceID").is(instanceId));

		liveChats =mongoTemplate.find(query, LiveChats.class);
		
		return liveChats;
	}

	@Override
	public LiveChats readBy(String instanceId) {
		LiveChats liveChats=null;
		/*Query query = new Query();

		query.addCriteria(Criteria.where("instanceID").is("instanceId"));*/

		liveChats = readByInstanceId(instanceId).get(0);//mongoTemplate.findOne(query, LiveChats.class);
		
		return liveChats;
	}

}
