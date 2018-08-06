package com.connecticus.chatapi.bean;

import java.util.List;

public class JsonResult {

	
	private Messages message;
	List<Messages> messages;

	public Messages getMessage() {
		return message;
	}

	public void setMessage(Messages message) {
		this.message = message;
	}

	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}

	
	
}
