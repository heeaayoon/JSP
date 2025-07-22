<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var webSocket = new WebSocket("<%=application.getInitParameter("CHAT_ADDR")%>/ChatingServer");
var chatWindow, chatMessage, chatId;

window.onload = function(){
	chatWindow = document.getElementById("chatWindow");
	chatMessage = document.getElementById("chatMessage");
	chatId = document.getElementById("chatId").value;
}

function sendMessage(){
	chatWindow.innerHTML += "<div class='myMsg'>"+chatMessage.value+"</div>"
	webSocket.send(chatId+'|'+chatMessage.value);
	chatMessage.value = "";
	chatWindow.scrollTop = chatWindow.scrollHeight;
}

function disconnect(){
	webSocket.close()
}

function enterKey(){
	if(window.event.keyCode ==13){
		sendMessage();
	}
}

webSocket.onopen = function(event){
	chatWindow.innerHTML += "웹소켓 서버에 연결되었습니다.<br/>";
}

webSocket.onclose = function(event){
	chatWindow.innerHTML += "웹소켓 서버가 종료되었습니다.<br/>";
}

webSocket.onerror = function(event){
	alert(event.data)
	chatWindow.innerHTML += "채팅 중 에러가 발생했습니다.<br/>";
}

webSocket.onmessage = function(event){
	var message = event.data.split("|");
	var sender = message[0];
	var content = message[1];
	if(content != ""){ //메세지가 빈 값인지 아닌지 확인
		if(content.match("/")){ //귓속말인지 아닌지 확인(/가 포함되어 있는지)
			if(content.match("/"+chatId)){ //자신에게 보낸 것인지 확인
				var temp = content.replace(("/"+chatId),"님의 귓속말입니다 :: "); //("/"+chatId) 부분을 "[귓속말] : "로 대체하기
				chatWindow.innerHTML += "<div>"+sender+""+temp+"</div>"; //"보낸사람 [귓속말] :: content내용" 형식으로 채팅창에 출력됨
			}
		}else{ //귓속말이 아닌 경우의 출력
			chatWindow.innerHTML += "<div>"+sender+" : "+content+"</div>"; //"보낸사람 : content내용" 형식으로 채팅창에 출력됨
		}
	}
	chatWindow.scrollTop = chatWindow.scrollHeight;
}
</script>
<style>
#chatWindow{border:1px solid black; width:270px; height:310px; overflow:scroll; padding:5px;}
#chatMessage{width:236px; height:30px;}
#sendBtn{height: 30px; position: relative; top:2px; left: -2px;}
#closeBtn{margin-bottom: 3px; position: relative; top:2px; left: -2px; }
#chatId{width: 158px; height: 24px; border: 1px solid #AAAAAA; background-color: #EEEEEE}
.myMsg{text-align: right;}
</style>
</head>
<body>
	대화명 : <input type="text" id="chatId" value="${param.chatId }" readonly />
	<button id="closeBtn" onclick="disconnect();">채팅종료</button>
	<div id = "chatWindow"></div>
	<div>
		<input type="text" id="chatMessage" onkeyup="enterKey();" />
		<button id="sendBtn" onclick="sendMessage();">전송</button>
	</div>
</body>
</html>