<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	function chatwinOpen(){
		var id = document.getElementById("chatId");
		if(id.value==""){
			alert("대화명 입력후 채팅창을 열어주세요.")
			id.focus();
			return;
		}
		window.open("ChatWindow.jsp?chatId="+id.value,"","width=320,height=400");
		id.value = "";
	}
	</script>
	<h2>웹소켓 채팅</h2>
	대화명 : <input type="text" id ="chatId" />
	<button onclick="chatwinOpen();">채팅 참여 </button>
</body>
</html>