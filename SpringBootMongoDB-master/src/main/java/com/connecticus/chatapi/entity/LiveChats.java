package com.connecticus.chatapi.entity;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.mongodb.BasicDBObject;

@Document(collection = "liveChats")
public class LiveChats {

	@Id
	String id;
	Integer chatId;
	String liveAgent;
	String user;
	Date createdOn;
	String instanceID;
	Date timeStamp;
	String timeStamp1;
	Date currentTime;
	String status;
	String msgRead;
	List<LiveChatsMessage> liveChatsMessage;
	LiveChatsMessage liveChatsMessageObj;

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

	public List<LiveChatsMessage> getLiveChatsMessage() {
		return liveChatsMessage;
	}

	public void setLiveChatsMessage(List<LiveChatsMessage> liveChatsMessage) {
		this.liveChatsMessage = liveChatsMessage;
	}

	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	public LiveChatsMessage getLiveChatsMessageObj() {
		return liveChatsMessageObj;
	}

	public void setLiveChatsMessageObj(LiveChatsMessage liveChatsMessageObj) {
		this.liveChatsMessageObj = liveChatsMessageObj;
	}

	public String getTimeStamp1() {
		return timeStamp1;
	}

	public void setTimeStamp1(String timeStamp1) {
		this.timeStamp1 = timeStamp1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public String getMsgRead() {
		return msgRead;
	}

	public void setMsgRead(String msgRead) {
		this.msgRead = msgRead;
	}

	@Override
	public String toString() {
		return "LiveChats [id=" + id + ", chatId=" + chatId + ", liveAgent=" + liveAgent + ", user=" + user
				+ ", createdOn=" + createdOn + ", instanceID=" + instanceID + ", timeStamp=" + timeStamp
				+ ", timeStamp1=" + timeStamp1 + ", currentTime=" + currentTime + ", status=" + status + ", msgRead="
				+ msgRead + ", liveChatsMessage=" + liveChatsMessage + ", liveChatsMessageObj=" + liveChatsMessageObj
				+ "]";
	}

	
}
