package com.connecticus.chatapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "liveChatTrigger")
public class LiveChatTrigger {
	
	
	@Id
	String id;
	String sessionId;
	String user;
	String handoverDecision;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getHandoverDecision() {
		return handoverDecision;
	}
	public void setHandoverDecision(String handoverDecision) {
		this.handoverDecision = handoverDecision;
	}
	

}
