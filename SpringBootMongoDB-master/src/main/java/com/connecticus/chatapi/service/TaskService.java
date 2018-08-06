package com.connecticus.chatapi.service;

import java.util.List;

import com.connecticus.chatapi.entity.User;
import com.connecticus.chatapi.entity.UserTask;

public interface TaskService {

	User CreateUser(User user);

	List<User> readByUserId(Integer userId);

	User getUser(Integer userId);

	User insertTask(User user1, UserTask userTask);

}
