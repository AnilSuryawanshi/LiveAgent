package com.connecticus.chatapi.service;

import java.util.Date;
import java.util.List;

import com.connecticus.chatapi.bean.AgentMessageBean;
import com.connecticus.chatapi.bean.JSONResponse;
import com.connecticus.chatapi.bean.LiveChatDto;
import com.connecticus.chatapi.bean.LiveChatsRequest;
import com.connecticus.chatapi.bean.Result;
import com.connecticus.chatapi.bean.ResultResponse;
import com.connecticus.chatapi.entity.AgentMessage;
import com.connecticus.chatapi.entity.LiveChatTrigger;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.LiveChatsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.WriteResult;

public interface ChatService {

	public List<LiveChats> findAll();

	// public List<LiveChats> findOne(Integer chatId);

	public LiveChats readChatById(String id);

	// public LiveChats save(LiveChatsRequest liveChat);

	public LiveChats insert(Result result);

	public List<LiveChats> readByInstanceId(String instanceID);

	// public LiveChats save(String sessionId, String utterance, String user);

	public LiveChats save(LiveChats r, String utterance, String type, Date currentTime);

	public List<LiveChats> readLatestChat(String instanceID);

	public LiveChatTrigger liveAgentTrigger(String handoverDecision, String sessionId, String user);

	public LiveChats saveAgentMessage(LiveChatsRequest liveChatsRequest);

	public LiveChatDto findOne(String instanceID);

	public AgentMessage saveAgentUtterance(AgentMessageBean agentMessageBean);

	public List<LiveChats> readLatestAgentChat(String instanceId);

	public WriteResult update(String instanceId);
	
	public LiveChats getLiveChat(String instanceId) throws JsonProcessingException;

}
