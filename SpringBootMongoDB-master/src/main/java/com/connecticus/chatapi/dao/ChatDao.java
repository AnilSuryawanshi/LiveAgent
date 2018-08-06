package com.connecticus.chatapi.dao;

import java.util.List;

import com.connecticus.chatapi.bean.JSONResponse;
import com.connecticus.chatapi.bean.LiveChatDto;
import com.connecticus.chatapi.entity.AgentMessage;
import com.connecticus.chatapi.entity.LiveChatTrigger;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.LiveChatsMessage;

public interface ChatDao {

	//List<LiveChats> readChatByChatId(Integer chatId);

	List<LiveChats> readByInstanceId(String instanceID);

	List<LiveChats> readLatestChat(String instanceID);

	LiveChatTrigger liveAgentTrigger(String handoverDecision, String sessionId, String user);

	LiveChatDto readChatByChatId(String instanceID);

	List<LiveChats> readLatestAgentChat(String instanceId);

	LiveChats readBy(String instanceId);



}
