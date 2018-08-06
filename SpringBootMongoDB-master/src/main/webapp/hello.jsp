<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>

body {
    font:12px arial;
    color: #222;
    text-align:center;
    padding:35px; }
  
form, p, span {
    margin:0;
    padding:0; }
  
input { font:12px arial; }
  
a {
    color:#0000FF;
    text-decoration:none; }
  
    a:hover { text-decoration:underline; }
  
#wrapper, #loginform {
    margin:0 auto;
    padding-bottom:25px;
    background:#EBF4FB;
    width:504px;
    border:1px solid #ACD8F0; }
  
#loginform { padding-top:18px; }
  
    #loginform p { margin: 5px; }
  
#chatbox {
    text-align:left;
    margin:0 auto;
    margin-bottom:25px;
    padding:10px;
    background:#fff;
    height:270px;
    width:430px;
    border:1px solid #ACD8F0;
    overflow:auto; }
  
#usermsg {
    width:395px;
    border:1px solid #ACD8F0; }
  
#submit { width: 60px; }
  
.error { color: #ff0000; }
  
#menu { padding:12.5px 25px 12.5px 25px; }
  
.welcome { float:left; }
  
.logout { float:right; }
  
.msgln { margin:0 0 2px 0; }
</style>
</head>
<body>

<h2>Chat Messages</h2>

<div id="wrapper">
    <div id="menu">
        <h1 class="welcome">Welcome, <b></b></h1>
        <p class="logout"><a id="exit" href="#">Exit Chat</a></p>
        <div style="clear:both"></div>
    </div>
    		
     
    <div id="chatbox" >
    
<c:forEach var="chat" items="${liveChats}">

	<c:forEach var="chats" items="${chat.liveChatsMessage}">
	
	<b>User:${chats.userMessage}</b><br><br>
	<b style="margin-left: 280px;">LiveAgent:${chats.liveAgentMessage}</b><br>
	</c:forEach>

</c:forEach>
    
      </div>
     
    <form:form name="message" method="POST" action="read-allChats" modelAttribute="liveChats" >
         <input name="agentMessage" type="text" id="usermsg" size="65" /> 
         <input name="instanceID" type="text" id="instanceID" size="15" /><br>
<!--           <input name="liveChatsMessage.liveAgentMessage" type="text" id="usermsg" size="63" />
 -->         
        
        <input name="submitmsg" type="submit"  id="submitmsg" value="Send" />
        
        
    </form:form>
    </div>
   
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script type="text/javascript">
/* setTimeout(function() {
	  location.reload();
	}, 5000);
 */
</script>
</body>

</body>
    
</html>
