package com.connecticus.chatapi.bean;

import java.sql.Timestamp;

public class AgentMessageBean {
	
	
	String id;
	String liveAgentMessage;
	String user;
	String instanceID;
	String timeStamp;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLiveAgentMessage() {
		return liveAgentMessage;
	}
	public void setLiveAgentMessage(String liveAgentMessage) {
		this.liveAgentMessage = liveAgentMessage;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	

}
