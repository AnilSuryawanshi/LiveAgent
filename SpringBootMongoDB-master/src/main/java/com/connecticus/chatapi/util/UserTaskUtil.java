package com.connecticus.chatapi.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.connecticus.chatapi.entity.User;
import com.connecticus.chatapi.entity.UserTask;

@Component
public class UserTaskUtil {

	public static User insertTask(User user1, UserTask userTask) {

		List<UserTask> userTaskList=new ArrayList<UserTask>();
		for(int i=0;i<user1.getUserTaskList().size();i++){
			
			UserTask userTask1=new UserTask();
			
			userTask1.setTaskName(user1.getUserTaskList().get(i).getTaskName());
			userTask1.setTaskDescription(user1.getUserTaskList().get(i).getTaskDescription());
			
			userTaskList.add(userTask1);
			
		}
		UserTask userTask1=new UserTask();

		userTask1.setTaskName(userTask.getTaskName());
		userTask1.setTaskDescription(userTask.getTaskDescription());
		userTaskList.add(userTask1);
		
		user1.setUserTaskList(userTaskList);
		
		return user1;
	}

	

}
