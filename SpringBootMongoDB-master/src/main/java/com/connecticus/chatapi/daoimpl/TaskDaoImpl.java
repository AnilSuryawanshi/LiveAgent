package com.connecticus.chatapi.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.connecticus.chatapi.dao.TaskDao;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.User;
import com.connecticus.chatapi.entity.UserTask;
import com.connecticus.chatapi.repository.TaskRepository;

@Repository
public class TaskDaoImpl implements TaskDao {
	
	
	@Autowired
	TaskRepository taskRepository;
	
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public User createUser(User user) {
		
		User user1=new User();
		UserTask userTask=new UserTask();
		List<UserTask> userTaskList=new ArrayList<UserTask>();
		
		user1.setUserId(user.getUserId());
		user1.setUserName(user.getUserName());
		user1.setUserEmail(user.getUserEmail());
		userTask.setUserId(user.getUserId());
		userTaskList.add(userTask);
		user1.setUserTaskList(userTaskList);
		
		user1=taskRepository.save(user1);
		
		return user1;
	}

	@Override
	public List<User> readByUser(Integer userId) {
		
		List<User> user = null;
		Query query = new Query();

		query.addCriteria(Criteria.where("userId").is(userId));

		user = mongoTemplate.find(query, User.class);

		
		return user;
	}

	@Override
	public User getUser(Integer userId) {

User user=null;

user=readByUser(userId).get(0);
		
		
		return user;
	}

}
