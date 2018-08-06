package com.connecticus.chatapi.serviceimpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connecticus.chatapi.bean.AgentMessageBean;
import com.connecticus.chatapi.bean.JSONResponse;
import com.connecticus.chatapi.bean.LiveChatDto;
import com.connecticus.chatapi.bean.LiveChatsRequest;
import com.connecticus.chatapi.bean.Result;
import com.connecticus.chatapi.bean.ResultResponse;
import com.connecticus.chatapi.dao.ChatDao;
import com.connecticus.chatapi.entity.AgentMessage;
import com.connecticus.chatapi.entity.LiveChatTrigger;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.LiveChatsMessage;
import com.connecticus.chatapi.repository.AgentMessageRepository;
import com.connecticus.chatapi.repository.LiveChatsRepository;
import com.connecticus.chatapi.service.ChatService;
import com.connecticus.chatapi.util.AgentMessageUtil;
import com.connecticus.chatapi.util.AgentUtteranceUtil;
import com.connecticus.chatapi.util.DialogUtil;
import com.connecticus.chatapi.util.LiveChatsUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	LiveChatsRepository liveChatsRepository;

	@Autowired
	LiveChatsUtil liveChatsUtil;

	@Autowired
	ChatDao chatDao;

	@Autowired
	DialogUtil dialogUtil;

	@Autowired
	AgentMessageUtil agentMessageUtil;

	@Autowired
	AgentUtteranceUtil agentUtteranceUtil;

	@Autowired
	AgentMessageRepository agentMessageRepository;

	Gson gson = new Gson();

	/*
	 * @Override public LiveChats save(String sessionId, String utterance,
	 * String user) {
	 * 
	 * LiveChats liveChats = LiveChatsUtil.createChat(sessionId,utterance,user);
	 * 
	 * return liveChatsRepository.save(liveChats);
	 * 
	 * }
	 */

	@Override
	public List<LiveChats> findAll() {
		return liveChatsRepository.findAll();

	}

	@Override
	public LiveChats readChatById(String id) {

		return liveChatsRepository.findOne(id);

	}

	@Override
	public LiveChats insert(Result result) {
		System.out.println("seriveimpl>>>>>>" + gson.toJson(result));
		LiveChats liveChats = dialogUtil.saveDialog(result);

		return liveChatsRepository.save(liveChats);

	}

	@Override
	public List<LiveChats> readByInstanceId(String instanceID) {

		List<LiveChats> liveChats = null;

		liveChats = chatDao.readByInstanceId(instanceID);

		return liveChats;
	}

	@Override
	public LiveChats save(LiveChats r, String utterance, String type,Date currentTime) {

		LiveChats liveChats = LiveChatsUtil.createChat(r, utterance, type,currentTime);

		return liveChatsRepository.save(liveChats);
	}

	@Override
	public List<LiveChats> readLatestChat(String instanceID) {
		List<LiveChats> liveChats = null;

		liveChats = chatDao.readLatestChat(instanceID);

		return liveChats;

	}

	@Override
	public LiveChatTrigger liveAgentTrigger(String handoverDecision, String sessionId, String user) {

		LiveChatTrigger liveChatTrigger = null;

		liveChatTrigger = chatDao.liveAgentTrigger(handoverDecision, sessionId, user);

		return liveChatTrigger;
	}

	@Override
	public LiveChats saveAgentMessage(LiveChatsRequest liveChatsRequest) {
		LiveChats liveChats = AgentMessageUtil.saveAgentMessage(liveChatsRequest);
		return liveChatsRepository.save(liveChats);
	}

	@Override
	public LiveChatDto findOne(String instanceID) {
		LiveChatDto liveChats = null;

		liveChats = chatDao.readChatByChatId(instanceID);
		return liveChats;
	}

	@Override
	public AgentMessage saveAgentUtterance(AgentMessageBean agentMessageBean) {
		AgentMessage agentMessage = AgentUtteranceUtil.saveAgentUtterance(agentMessageBean);

		return agentMessageRepository.save(agentMessage);
	}

	@Override
	public List<LiveChats> readLatestAgentChat(String instanceId) {

		List<LiveChats> liveChats = null;

		liveChats = chatDao.readLatestAgentChat(instanceId);

		return liveChats;
	}

	@Override
	public WriteResult update(String instanceId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH.mm.ss");

		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        System.out.println(sdf.format(timestamp));
	        MongoClient mongo = new MongoClient("localhost", 27017);
	        DB db = mongo.getDB("LiveChats");
			DBCollection col = db.getCollection("liveChats");
			
			//Add a new field in a single document
			DBObject query = new BasicDBObject("instanceID", instanceId);
			DBObject update = new BasicDBObject();
			update.put("$set", new BasicDBObject("timeStamp1",sdf.format(timestamp)));
			WriteResult result = col.updateMulti(query, update);
			mongo.close();
			
		return result;
	}

	@Override
	public LiveChats getLiveChat(String instanceId) throws JsonProcessingException {
		
		LiveChats liveChats = null;
		
		liveChats = chatDao.readBy(instanceId);

		//liveChats=liveChatsRepository.findById(instanceId);'
		/*System.out.println(new ObjectMapper().writeValueAsString(liveChats));
		Result r = new Gson().fromJson(new ObjectMapper().writeValueAsString(liveChats), Result.class);*/
		return liveChats;
	}

}
