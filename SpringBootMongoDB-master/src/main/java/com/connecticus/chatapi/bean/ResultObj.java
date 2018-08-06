package com.connecticus.chatapi.bean;

public class ResultObj {
	
	private CurrentEntity currentEntity;

    private String speech;

    private String query;

    private String reply;

    private String[] contexts;

    private Entities[] entities;

    private Intent intent;

	public CurrentEntity getCurrentEntity() {
		return currentEntity;
	}

	public void setCurrentEntity(CurrentEntity currentEntity) {
		this.currentEntity = currentEntity;
	}

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String[] getContexts() {
		return contexts;
	}

	public void setContexts(String[] contexts) {
		this.contexts = contexts;
	}

	public Entities[] getEntities() {
		return entities;
	}

	public void setEntities(Entities[] entities) {
		this.entities = entities;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}
    
    

}
