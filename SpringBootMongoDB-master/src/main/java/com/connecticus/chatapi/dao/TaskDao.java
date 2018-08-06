package com.connecticus.chatapi.dao;

import java.util.List;

import com.connecticus.chatapi.entity.User;

public interface TaskDao {

 public User createUser(User user);

public List<User> readByUser(Integer userId);

public User getUser(Integer userId);

}
