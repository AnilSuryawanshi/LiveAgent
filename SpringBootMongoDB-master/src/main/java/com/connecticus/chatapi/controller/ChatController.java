package com.connecticus.chatapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.connecticus.chatapi.bean.Agent;
import com.connecticus.chatapi.bean.Data;
import com.connecticus.chatapi.bean.Info;
import com.connecticus.chatapi.bean.JsonResult;
import com.connecticus.chatapi.bean.LiveChatDto;
import com.connecticus.chatapi.bean.LiveChatsMessage;
import com.connecticus.chatapi.bean.LiveChatsRequest;
import com.connecticus.chatapi.bean.Messages;
import com.connecticus.chatapi.bean.ResponseBean;
import com.connecticus.chatapi.bean.Result;
import com.connecticus.chatapi.entity.LiveChatTrigger;
import com.connecticus.chatapi.entity.LiveChats;
import com.connecticus.chatapi.service.ChatService;
import com.connecticus.chatapi.util.LiveChatsUtil;
import com.connecticus.chatapi.util.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @author Dinesh.Rajput
 *
 */
@Controller
@RequestMapping("/chat")
@PropertySource("classpath:application.properties")
public class ChatController {

	@Autowired
	ChatService chatService;

	@Autowired
	Environment env;

	Gson gson = new Gson();

	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders() {
			private static final long serialVersionUID = 1L;

		};
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		return headers;
	}

	/**
	 * POST /create --> Create a new chat and save it in the database.
	 */
	@CrossOrigin
	@RequestMapping(value = "/createChat", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> createChat(@RequestParam String sessionId, String utterance, String user) {
		ResponseBean responseBean = new ResponseBean();
		JsonResult currencyReportResult = new JsonResult();
		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();

		System.out.println("Utterance>>>>>>>>>>>>>>" + utterance);

		try {
			Date currentTime = new Date();
			LiveChats liveChats = chatService.getLiveChat(sessionId);

			LiveChats liveChat = chatService.save(liveChats, utterance, "User", currentTime);
			message.setData(data);
			message.setFlag("2");
			message.setChatMsg("ChatMessage");
			data.setError("");
			info.setImage("");
			info.setAudio("");
			info.setVideo("");
			info.setFile("");
			info.setText("createChat");
			info.setDocuments("");
			data.setInfo(info);
			currencyReportResult.setMessage(message);
			responseBean.setPayload(currencyReportResult);
			responseBean.setStatus(StatusCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on craeteCaht" + e);
		}
		return new ResponseEntity<JsonResult>(currencyReportResult, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/creChat", method = RequestMethod.POST) public
	 * ResponseEntity<JsonResult> creChat(@RequestParam String utterance) {
	 * System.out.println("Utterance>>>>>>>>>>>>>>" + utterance); JsonResult
	 * currencyReportResult = new JsonResult(); ResponseBean responseBean = new
	 * ResponseBean(); Messages message = new Messages(); Info info = new
	 * Info(); Data data = new Data(); LiveChatsRequest liveChatsRequest = new
	 * LiveChatsRequest(); try { liveChatsRequest.setUser("rohit");
	 * liveChatsRequest.setInstanceID("d1-OBAIOAME7UCN");
	 * liveChatsRequest.setAgentMessage(utterance);
	 * 
	 * Date now = new Date(); System.out.println("dsac" + now);
	 * liveChatsRequest.setTimeStamp(now);
	 * 
	 * LiveChats liveChat = chatService.saveAgentMessage(liveChatsRequest); //
	 * String instanceID = liveChatsRequest.getInstanceID(); String instanceID =
	 * "d2-M1DPNM7JJUOG"; // Thread.sleep(5000); // LiveChats liveChats =
	 * chatService.readLatestChat(instanceID); //
	 * message.setChat(liveChats.getLiveChatsMessage().get(0).
	 * getLiveAgentMessage()); message.setData(data); message.setFlag("1");
	 * data.setError(""); info.setImage(""); info.setAudio("");
	 * info.setVideo(""); info.setFile(""); info.setText("");
	 * info.setDocuments(""); data.setInfo(info);
	 * 
	 * currencyReportResult.setMessage(message);
	 * System.out.println("currencyReportResult--------------->>>>" +
	 * currencyReportResult.getMessage());
	 * 
	 * responseBean.setPayload(currencyReportResult);
	 * responseBean.setStatus(StatusCode.SUCCESS); } catch (Exception e) {
	 * e.printStackTrace(); System.out.println("Error on craeteCaht" + e); }
	 * return new ResponseEntity<JsonResult>(currencyReportResult,
	 * HttpStatus.OK); }
	 */

	/**
	 * GET /read --> Read all chats from the database.
	 */
	@RequestMapping(value = "/read-all", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> readAll() {
		List<LiveChats> liveChats = chatService.findAll();
		ResponseBean responseBean = new ResponseBean();
		responseBean.setPayload(liveChats);
		responseBean.setStatus(StatusCode.SUCCESS);
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/readChat", method = RequestMethod.GET)
	public ResponseEntity<LiveChatDto> readChat(@RequestParam String instanceID) {
		ResponseBean responseBean = new ResponseBean();		//LiveChatsMessage liveChats=new LiveChatsMessage();
		LiveChatDto liveChats = chatService.findOne(instanceID);
		responseBean.setPayload(liveChats);
		responseBean.setStatus(StatusCode.SUCCESS);
		return new ResponseEntity<LiveChatDto>(liveChats, HttpStatus.OK);
	}

	@RequestMapping(value = "/readChatById", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> readChatById(@RequestParam String id) {
		ResponseBean responseBean = new ResponseBean();
		LiveChats liveChats = chatService.readChatById(id);
		responseBean.setPayload(liveChats);
		responseBean.setStatus(StatusCode.SUCCESS);
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}

	@RequestMapping("/agentOpen")
	public String agentOpen(Model model) {

		return "agentOpen";
	}

	@RequestMapping(value = "/sendChat", method = RequestMethod.POST)
	public String hello(@ModelAttribute("liveChats") LiveChatsRequest liveChats, Model model) {

		liveChats.setCreatedOn(new Date());
		// liveChats.getLiveChatsMessage().setLiveAgentMessage("Hello User");
		;
		/*
		 * LiveChats liveChat = chatService.save(liveChats);
		 * responseBean.setPayload(liveChat);
		 */

		// List<LiveChats> chat = chatService.findOne(liveChat.getChatId());

		// model.addAttribute("liveChats", chat);

		return "hello";

	}

	// Get dialog Method
	@RequestMapping(value = "/getDialog", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getDialog(@RequestParam String sessionId) throws Exception {

		// String url="http://192.168.1.206:9009/nessa/bot/"+ sessionId + "/" +
		// "getDialog";
		JsonResult currencyReportResult = new JsonResult();
		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();
		Date date = new Date();

		String url1 = env.getProperty("base_path");
		String url = url1 + sessionId + "/" + "getDialog";
		System.out.println("url>>>" + url);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers = this.createHeaders();
		ResponseBean responseBean = new ResponseBean();

		String str = "";
		try {

			HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers),String.class);

			System.out.println("response................." + response);

			ObjectMapper mapper = new ObjectMapper();
			System.out.println("body................." + response.getBody());
			String result1 = response.getBody();

			Gson g = new Gson();
			Result result = g.fromJson(result1, Result.class);

			System.out.println("resultttt>>>>>>>>" + gson.toJson(result));

			LiveChats liveChats = chatService.insert(result);

			message.setData(data);
			data.setError("");
			message.setChat("Hello, What can we do to serve you better?");
			info.setImage("");
			info.setAudio("");
			info.setVideo("");
			info.setFile("");
			info.setText("");
			info.setDocuments("");
			data.setInfo(info);

			currencyReportResult.setMessage(message);
			responseBean.setPayload(currencyReportResult);
			responseBean.setStatus(StatusCode.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<JsonResult>(currencyReportResult, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/readByInstanceId", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> readByInstanceId(@RequestParam String instanceID, HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		JsonResult currencyReportResult = new JsonResult();
		System.out.println("kkk" + instanceID);
		List<Messages> message = new ArrayList<Messages>();
		List<LiveChats> liveChats = chatService.readByInstanceId(instanceID);

		for (LiveChats liveChats2 : liveChats) {

			if (liveChats2.getLiveChatsMessage() != null) {
				for (com.connecticus.chatapi.entity.LiveChatsMessage liveChatsMessage : liveChats2
						.getLiveChatsMessage()) {
					Messages msg = new Messages();
					msg.setChat(liveChatsMessage.getUserMessage());
					msg.setChatMsg(liveChatsMessage.getLiveAgentMessage());
					message.add(msg);
					request.getSession().setAttribute("lastFetchTime",liveChatsMessage.getCurrentTime());
				}
				currencyReportResult.setMessages(message);

			}

		}

		responseBean.setPayload(currencyReportResult);
		responseBean.setStatus(StatusCode.SUCCESS);
		return new ResponseEntity<JsonResult>(currencyReportResult, HttpStatus.OK);

	}

	@RequestMapping(value = "/read-allChats", method = RequestMethod.POST)
	public String readAllChats(@ModelAttribute("liveChats") LiveChatsRequest liveChats, Model model) {

		liveChats.setInstanceID("d1-OBAIOAME7UCN");
		// LiveChats liveChat = chatService.save(liveChats);

		String instanceID = liveChats.getInstanceID();
		List<LiveChats> chat = chatService.readByInstanceId(instanceID);
		System.out.println("chat>>>>>>>>>>>>>>>" + chat);

		model.addAttribute("liveChats", chat);

		return "hello";

	}

	@RequestMapping("/agentWindow")
	public String agentWindow(Model model) {

		return "agentWindow";
	}

	@RequestMapping(value = "/liveAgentTrigger", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> liveAgentTrigger(@RequestParam String handoverDecision, String sessionId,
			String user) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		JsonResult currencyReportResult = new JsonResult();
		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();
		Date date = new Date();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers = this.createHeaders();
		

		LiveChatTrigger liveChatTrigger = chatService.liveAgentTrigger(handoverDecision, sessionId, user);
		
		
		ResponseEntity<JsonResult> result=getDialog(sessionId);
		
		String status="busy";
		
		String url1 = env.getProperty("sendAgentStatus");
		String url = url1 + sessionId +"&status="+status;
		System.out.println("url>>>" + url);
		
		HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers),
				String.class);
		
		
		if(response.getBody()==null){
			
			message.setData(data);
			data.setError("");
			message.setChat("All of our agents are busy. Kindly wait for sometime. Thank you.");
			message.setFlag("2");
			info.setImage("");
			info.setAudio("");
			info.setVideo("");
			info.setFile("");
			info.setText("");
			info.setDocuments("");
			data.setInfo(info);
			currencyReportResult.setMessage(message);


		}else{
		
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("body................." + response.getBody());
		String result1 = response.getBody();
		 gson=new Gson();
		
		Agent agent = gson.fromJson(result1, Agent.class);
		
		String msg="Thank you for contacting Nessa support. You are now connected with our Live Agent,How can we help you today?";
		
		message.setData(data);
		data.setError("");
		message.setChat(msg);
		message.setChatMsg("agentDefaultMsg");
		message.setFlag("1");
		info.setImage("");
		info.setAudio("");
		info.setVideo("");
		info.setFile("");
		info.setText("");
		info.setDocuments("");
		data.setInfo(info);

		currencyReportResult.setMessage(message);
		responseBean.setPayload(currencyReportResult);
		responseBean.setStatus(StatusCode.SUCCESS);
		}
		return new ResponseEntity<JsonResult>(currencyReportResult, HttpStatus.OK);

	
	}

	@RequestMapping(value = "/sendChat", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> sendChat(@RequestParam String utterance, String sessionId, String user) {
		ResponseBean responseBean = new ResponseBean();

		/*
		 * Messages message = new Messages(); Info info = new Info(); Data data
		 * = new Data(); //List<LiveChatsMessage> liveChatMessageList = new
		 * ArrayList<LiveChatsMessage>();
		 */
		LiveChatsMessage liveChatsMessage1 = new LiveChatsMessage();

		LiveChatsRequest liveChats = new LiveChatsRequest();
		liveChats.setUser(user);
		liveChats.setInstanceID(sessionId);
		liveChatsMessage1.setUserMessage(utterance);

		// LiveChats liveChat = chatService.save(liveChats);

		String instanceID = liveChats.getInstanceID();
		List<LiveChats> chat = chatService.readByInstanceId(instanceID);

		/*
		 * List<LiveChatsMessage> liveChatMessageList = new
		 * ArrayList<LiveChatsMessage>();
		 * 
		 * for (LiveChatsMessage liveChatsMessage2 : liveChatMessageList) {
		 * message.setChat(liveChatsMessage2.getLiveAgentMessage());
		 * message.setChatMsg(liveChatsMessage2.getUserMessage()); }
		 */

		responseBean.setPayload(chat);
		responseBean.setStatus(StatusCode.SUCCESS);
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);

	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> readAllChats() {

		ResponseBean responseBean = new ResponseBean();
		JsonResult currencyReportResult = new JsonResult();
		List<Messages> message = new ArrayList<Messages>();
		String instanceID = "d1-OBAIOAME7UCN";
		List<LiveChats> chat = chatService.readByInstanceId(instanceID);
		System.out.println("chat>>>>>>>>>>>>>>>" + chat);

		for (LiveChats liveChats : chat) {

			for (com.connecticus.chatapi.entity.LiveChatsMessage liveChatsMessage : liveChats.getLiveChatsMessage()) {
				Messages msg = new Messages();
				msg.setChat(liveChatsMessage.getUserMessage());
				msg.setChatMsg(liveChatsMessage.getLiveAgentMessage());
				message.add(msg);
			}
			currencyReportResult.setMessages(message);

		}

		responseBean.setPayload(currencyReportResult);
		responseBean.setStatus(StatusCode.SUCCESS);
		return new ResponseEntity<JsonResult>(currencyReportResult, HttpStatus.OK);

	}

	@CrossOrigin
	@RequestMapping(value = "/saveAgentUtterance", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> saveAgentUtterance(@RequestParam String utterance, String instanceId) {
		System.out.println("Utterance>>>>>>>>>>>>>>" + utterance);
		ResponseBean responseBean = new ResponseBean();
		JsonResult currencyReportResult = new JsonResult();

		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();
		Date date = new Date();

		try {

			LiveChats liveChats = chatService.getLiveChat(instanceId);
			Date currentTime = new Date();

			LiveChats liveChat = chatService.save(liveChats, utterance, "Agent", currentTime);

			Thread.sleep(2000);
			message.setData(data);
			message.setFlag("1");
			data.setError("");
			info.setImage("");
			info.setAudio("");
			info.setVideo("");
			info.setFile("");
			info.setText("");
			info.setDocuments("");
			data.setInfo(info);

			currencyReportResult.setMessage(message);
			System.out.println("currencyReportResult--------------->>>>" + currencyReportResult.getMessage());

			responseBean.setPayload(currencyReportResult);
			responseBean.setStatus(StatusCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on craeteCaht" + e);
		}
		return new ResponseEntity<JsonResult>(currencyReportResult, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/readLatestChat", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> readLatestChatByInstance(String instanceId, HttpServletRequest request) {
		System.out.println("instanceId>>>>>>>>>>>>>>" + instanceId);
		JsonResult currencyReportResult = new JsonResult();
		ResponseBean responseBean = new ResponseBean();
		String lastFetchTime = null;
		Info info = new Info();
		Data data = new Data();
		List<Messages> message = new ArrayList<Messages>();
		ArrayList<com.connecticus.chatapi.entity.LiveChatsMessage> livechatMessgeList = new ArrayList<com.connecticus.chatapi.entity.LiveChatsMessage>();
		Date lastSessionDate = null;
		try {
			HttpSession session = request.getSession();//create session 
			if(session.getAttribute("lastFetchTime") != null){
				lastFetchTime = session.getAttribute("lastFetchTime").toString();
				lastSessionDate =  (Date)session.getAttribute("lastFetchTime");
			}
			
			List<LiveChats> liveChats = chatService.readLatestChat(instanceId);

			for (LiveChats liveChats2 : liveChats) {
				if (liveChats2.getLiveChatsMessage() != null) {
					for (com.connecticus.chatapi.entity.LiveChatsMessage liveChatsMessage : liveChats2.getLiveChatsMessage()) {
						if(lastFetchTime == null){
							Messages msg = new Messages();
							msg.setChat(liveChatsMessage.getUserMessage());
							msg.setChatMsg(liveChatsMessage.getLiveAgentMessage());
							message.add(msg);
							//if(liveChatsMessage.getUserMessage() != null && liveChatsMessage.getLiveAgentMessage() == null)
							//livechatMessgeList.add(liveChatsMessage);
							session.setAttribute("lastFetchTime",liveChatsMessage.getCurrentTime());
						}
						else if(liveChatsMessage.getCurrentTime() != null && lastSessionDate.compareTo(liveChatsMessage.getCurrentTime()) < 0){
							Messages msg = new Messages();
							msg.setChat(liveChatsMessage.getUserMessage());
							msg.setChatMsg(liveChatsMessage.getLiveAgentMessage());
							message.add(msg);
							if(liveChatsMessage.getUserMessage() != null && liveChatsMessage.getLiveAgentMessage() == null)
							livechatMessgeList.add(liveChatsMessage);
							session.setAttribute("lastFetchTime",liveChatsMessage.getCurrentTime());
						}
					}
					currencyReportResult.setMessages(message);
				}
			}
			
			//if(livechatMessgeList.size()>0)
				liveChats.get(0).setLiveChatsMessage(livechatMessgeList);
			
			
			
			System.out.println("currencyReportResult--------------->>>>" + currencyReportResult.getMessages());

			responseBean.setPayload(liveChats);
			responseBean.setStatus(StatusCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on craeteCaht" + e);
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/readLatestAgentChat", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> readLatestAgentChat(@RequestParam String instanceId, HttpServletRequest request) {

		JsonResult currencyReportResult = new JsonResult();
		ResponseBean responseBean = new ResponseBean();
		String lastFetchTime = null;
		List<Messages> message = new ArrayList<Messages>();
		ArrayList<com.connecticus.chatapi.entity.LiveChatsMessage> livechatMessgeList = new ArrayList<com.connecticus.chatapi.entity.LiveChatsMessage>();
		Date lastSessionDate = null;

		try {
			
			//HttpSession session = request.getSession();
			HashMap<String, Date> session = LiveChatsUtil.getSession();
			//if(session.getAttribute("agentTime") != null){
			if(session.get("agentTime") != null){
				lastFetchTime = session.get("agentTime").toString();
				lastSessionDate =  (Date)session.get("agentTime");
			}
			
			List<LiveChats> liveChats = chatService.readLatestChat(instanceId);

			for (LiveChats liveChats2 : liveChats) {
				if (liveChats2.getLiveChatsMessage() != null) {
					for (com.connecticus.chatapi.entity.LiveChatsMessage liveChatsMessage : liveChats2.getLiveChatsMessage()) {
						if(lastFetchTime == null){
							Messages msg = new Messages();
							msg.setChat(liveChatsMessage.getUserMessage());
							msg.setChatMsg(liveChatsMessage.getLiveAgentMessage());
							message.add(msg);
							//if(liveChatsMessage.getLiveAgentMessage() != null && liveChatsMessage.getUserMessage() == null)

							//livechatMessgeList.add(liveChatsMessage);
							session.put("agentTime",liveChatsMessage.getCurrentTime());
						}
						else if(liveChatsMessage.getCurrentTime() != null && lastSessionDate.compareTo(liveChatsMessage.getCurrentTime()) < 0){
							Messages msg = new Messages();
							msg.setChat(liveChatsMessage.getUserMessage());
							msg.setChatMsg(liveChatsMessage.getLiveAgentMessage());
							message.add(msg);
							if(liveChatsMessage.getLiveAgentMessage() != null && liveChatsMessage.getUserMessage() == null)

							livechatMessgeList.add(liveChatsMessage);
							session.put("agentTime",liveChatsMessage.getCurrentTime());
						}
					}
					currencyReportResult.setMessages(message);
				}
			}
			
			//if(livechatMessgeList.size()>0)
			
				liveChats.get(0).setLiveChatsMessage(livechatMessgeList);

			responseBean.setPayload(liveChats);
			responseBean.setStatus(StatusCode.SUCCESS);
		} catch (Exception e) {
			System.out.println("ChatController: Error In method readLatestChat >>>>" + e);

		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);

	}
	
}
