package com.connecticus.chatapi.bean;

import java.io.Serializable;

import com.connecticus.chatapi.util.StatusCode;

public class ResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient Object payload;
	
	private StatusCode status;

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public StatusCode getStatus() {
		return status;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResponseBean [payload=" + payload + ", status=" + status + "]";
	} 
	
	
	
	

}
