package com.connecticus.chatapi.bean;

public class JSONResponse {
	
	 private ResultObj resultObj;

	    private String source;

	    private String timeStamp;

	    private String sessionId;

	    private ICard iCard;

	    private Action action;

	    private String language;

	    private String user;

		public ResultObj getResultObj() {
			return resultObj;
		}

		public void setResultObj(ResultObj resultObj) {
			this.resultObj = resultObj;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(String timeStamp) {
			this.timeStamp = timeStamp;
		}

		public String getSessionId() {
			return sessionId;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		public ICard getiCard() {
			return iCard;
		}

		public void setiCard(ICard iCard) {
			this.iCard = iCard;
		}

		public Action getAction() {
			return action;
		}

		public void setAction(Action action) {
			this.action = action;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

	    
	    

}
