package com.connecticus.chatapi.bean;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.LiveChatsMessage;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.mongodb.BasicDBObject;

public class LiveChatsRequest {

	String id;
	Integer chatId;
	String liveAgent;
	String user;
	Date createdOn;
	String instanceID;
	LiveChats liveChats;
	String userMessage;
	String agentMessage;
	Date timeStamp;
	String status;
	LiveChatsMessage liveChatsMessage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public String getLiveAgent() {
		return liveAgent;
	}

	public void setLiveAgent(String liveAgent) {
		this.liveAgent = liveAgent;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public LiveChats getLiveChats() {
		return liveChats;
	}

	public void setLiveChats(LiveChats liveChats) {
		this.liveChats = liveChats;
	}

	public LiveChatsMessage getLiveChatsMessage() {
		return liveChatsMessage;
	}

	public void setLiveChatsMessage(LiveChatsMessage liveChatsMessage) {
		this.liveChatsMessage = liveChatsMessage;
	}

	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getAgentMessage() {
		return agentMessage;
	}

	public void setAgentMessage(String agentMessage) {
		this.agentMessage = agentMessage;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
