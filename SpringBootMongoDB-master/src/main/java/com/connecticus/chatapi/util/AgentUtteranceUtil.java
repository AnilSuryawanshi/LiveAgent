package com.connecticus.chatapi.util;

import org.springframework.stereotype.Component;

import com.connecticus.chatapi.bean.AgentMessageBean;
import com.connecticus.chatapi.entity.AgentMessage;
@Component
public class AgentUtteranceUtil {

	public static AgentMessage saveAgentUtterance(AgentMessageBean agentMessageBean) {

		AgentMessage agentMessage=new AgentMessage();
		
		agentMessage.setInstanceID(agentMessageBean.getInstanceID());
		agentMessage.setUser(agentMessageBean.getUser());
		agentMessage.setLiveAgentMessage(agentMessageBean.getLiveAgentMessage());
		agentMessage.setTimeStamp(agentMessageBean.getTimeStamp());
		
		return agentMessage;
	}

}
