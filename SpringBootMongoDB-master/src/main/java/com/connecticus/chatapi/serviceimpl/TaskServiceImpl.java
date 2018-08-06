package com.connecticus.chatapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connecticus.chatapi.dao.TaskDao;
import com.connecticus.chatapi.entity.User;
import com.connecticus.chatapi.entity.UserTask;
import com.connecticus.chatapi.repository.TaskRepository;
import com.connecticus.chatapi.service.TaskService;
import com.connecticus.chatapi.util.UserTaskUtil;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskDao taskDao;

	@Autowired
	UserTaskUtil userTaskUtil;
	@Autowired
	TaskRepository taskRepository;

	@Override
	public User CreateUser(User user) {

		User user1 = taskDao.createUser(user);

		return user1;
	}

	@Override
	public List<User> readByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<User> user = null;
		user = taskDao.readByUser(userId);
		return user;
	}

	@Override
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = null;

		user = taskDao.getUser(userId);

		return user;
	}

	@Override
	public User insertTask(User user1, UserTask userTask) {
		User user = UserTaskUtil.insertTask(user1, userTask);

		return taskRepository.save(user);
	}

}
