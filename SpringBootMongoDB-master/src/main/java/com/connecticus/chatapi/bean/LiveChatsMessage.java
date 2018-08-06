package com.connecticus.chatapi.bean;

public class LiveChatsMessage {
	
	String chatId;
	String liveAgentMessage;
	String userMessage;
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getLiveAgentMessage() {
		return liveAgentMessage;
	}
	public void setLiveAgentMessage(String liveAgentMessage) {
		this.liveAgentMessage = liveAgentMessage;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	
	

}
