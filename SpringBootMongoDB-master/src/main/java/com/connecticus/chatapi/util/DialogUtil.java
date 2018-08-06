package com.connecticus.chatapi.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.connecticus.chatapi.bean.Result;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.LiveChatsMessage;
import com.google.gson.Gson;

@Component
public class DialogUtil {

	public static LiveChats saveDialog(Result result) {

		Gson gson = new Gson();
		System.out.println("dialogUtil>>>>>>" + gson.toJson(result));

		LiveChats liveChat = new LiveChats();
		List<LiveChatsMessage> liveChatMessageList = new ArrayList<LiveChatsMessage>();

		liveChat.setUser(result.getUser());
		liveChat.setInstanceID(result.getInstanceID());

		for (int i = 0; i < result.getDialog().length; i++) {
			LiveChatsMessage liveChatsMessage = new LiveChatsMessage();

			liveChatsMessage.setUserMessage(result.getDialog()[i].getU());
			System.out.println("forUser>>>>>>" + result.getDialog()[i].getU());

			liveChatsMessage.setLiveAgentMessage(result.getDialog()[i].getS());
			System.out.println("forAgent>>>>>>" + result.getDialog()[i].getS());

			liveChatMessageList.add(liveChatsMessage);

		}
		liveChat.setLiveChatsMessage(liveChatMessageList);

		return liveChat;
	}

}
