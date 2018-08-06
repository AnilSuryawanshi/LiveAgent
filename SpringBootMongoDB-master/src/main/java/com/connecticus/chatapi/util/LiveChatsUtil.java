package com.connecticus.chatapi.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.LiveChatsMessage;
import com.google.gson.Gson;

@Component
public class LiveChatsUtil {

	Logger logger = LoggerFactory.getLogger(LiveChatsUtil.class);
	public static HashMap<String, Date> session = new HashMap<String, Date>();
	public static HashMap<String, Date> getSession(){
		return session;
	}
	public static LiveChats createChat(LiveChats result, String utterance, String type, Date currentTime) {
		Gson gson = new Gson();
		System.out.println("dialogUtil>>>>>>" + gson.toJson(result));

		// LiveChats liveChat = new LiveChats();
		List<LiveChatsMessage> liveChatMessageList = new ArrayList<LiveChatsMessage>();

		// liveChat.setUser(result.getUser());
		// liveChat.setInstanceID(result.getInstanceID());

		for (int i = 0; i < result.getLiveChatsMessage().size(); i++) {
			LiveChatsMessage liveChatsMessage = new LiveChatsMessage();

			liveChatsMessage.setUserMessage(result.getLiveChatsMessage().get(i).getUserMessage());
			liveChatsMessage.setCurrentTime(result.getLiveChatsMessage().get(i).getCurrentTime());
			
			liveChatsMessage.setLiveAgentMessage(result.getLiveChatsMessage().get(i).getLiveAgentMessage());
			liveChatsMessage.setCurrentTime(result.getLiveChatsMessage().get(i).getCurrentTime());


			liveChatMessageList.add(liveChatsMessage);

		}
		LiveChatsMessage liveChatsMessage = new LiveChatsMessage();
		if (type.equals("User")) {
			liveChatsMessage.setUserMessage(utterance);
			liveChatsMessage.setCurrentTime(currentTime);
			
		} else{
			liveChatsMessage.setLiveAgentMessage(utterance);
			liveChatsMessage.setCurrentTime(currentTime);

	}
		liveChatMessageList.add(liveChatsMessage);
	
		result.setLiveChatsMessage(liveChatMessageList);

		return result;
	}

}
