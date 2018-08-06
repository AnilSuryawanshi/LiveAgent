package com.connecticus.chatapi.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.connecticus.chatapi.bean.LiveChatsRequest;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.LiveChatsMessage;

@Component
public class AgentMessageUtil {

	public static LiveChats saveAgentMessage(LiveChatsRequest liveChatsRequest) {
		LiveChats liveChat = new LiveChats();
		List<LiveChatsMessage> liveChatMessageList = new ArrayList<LiveChatsMessage>();
		
		LiveChatsMessage liveChatsMessage = new LiveChatsMessage();
		
		liveChatsMessage.setLiveAgentMessage(liveChatsRequest.getAgentMessage());
		liveChat.setUser(liveChatsRequest.getUser());
		liveChat.setTimeStamp(liveChatsRequest.getTimeStamp());
		liveChat.setInstanceID(liveChatsRequest.getInstanceID());
		liveChatMessageList.add(liveChatsMessage);
		liveChat.setLiveChatsMessage(liveChatMessageList);
		return liveChat;
	
	}

}
