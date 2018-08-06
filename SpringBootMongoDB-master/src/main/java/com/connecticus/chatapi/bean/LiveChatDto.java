package com.connecticus.chatapi.bean;

import java.util.Date;

public class LiveChatDto {
	
	Integer chatId;
	String liveAgentMessage;
	String userMessage;
	String user;
	Date currentTime;
	Date timeStamp1;
	String msgRead;
	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	public Date getTimeStamp1() {
		return timeStamp1;
	}
	public void setTimeStamp1(Date timeStamp1) {
		this.timeStamp1 = timeStamp1;
	}
	public String getMsgRead() {
		return msgRead;
	}
	public void setMsgRead(String msgRead) {
		this.msgRead = msgRead;
	}
	
	

}
