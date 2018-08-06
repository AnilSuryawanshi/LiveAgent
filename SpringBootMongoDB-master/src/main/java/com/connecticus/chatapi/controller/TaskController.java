package com.connecticus.chatapi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connecticus.chatapi.bean.Data;
import com.connecticus.chatapi.bean.Info;
import com.connecticus.chatapi.bean.JsonResult;
import com.connecticus.chatapi.bean.Messages;
import com.connecticus.chatapi.bean.ResponseBean;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.entity.User;
import com.connecticus.chatapi.entity.UserTask;
import com.connecticus.chatapi.service.TaskService;
import com.connecticus.chatapi.util.StatusCode;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	/**
	 * POST /create --> Create a new chat and save it in the database.
	 */
	@RequestMapping(value = "/CreateUser", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> createChat(@RequestBody User user) {
		ResponseBean responseBean = new ResponseBean();
		


		try {
			User user1 = taskService.CreateUser(user);

			responseBean.setPayload(user1);
			responseBean.setStatus(StatusCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on craeteCaht" + e);
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/readByUserId", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> readByUserId(@RequestParam Integer userId) {
		ResponseBean responseBean = new ResponseBean();
		List<User> user = taskService.readByUserId(userId);
		responseBean.setPayload(user);
		responseBean.setStatus(StatusCode.SUCCESS);
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/insertTask", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> insertTask(@RequestBody UserTask userTask) {
		ResponseBean responseBean = new ResponseBean();
		
			Integer userId=3;

		try {
			User user1 = taskService.getUser(userId);
			
			User user=taskService.insertTask(user1,userTask);

			responseBean.setPayload(user1);
			responseBean.setStatus(StatusCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on craeteCaht" + e);
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
	

}
