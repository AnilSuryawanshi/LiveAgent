package com.connecticus.chatapi.bean;

public class ResultResponse {
	
	private String timeStamp;

    private String userAgent;

    private String instanceID;

    private Dialog[] dialog;

    private String clientIP;

    private String user;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	public Dialog[] getDialog() {
		return dialog;
	}

	public void setDialog(Dialog[] dialog) {
		this.dialog = dialog;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
    
    

}
