<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
if(session.getAttribute("UserId")==null){ //세션 영역에 UserId 라는 속성값이 있는지 확인
	JSFunction.alertLocation("로그인 후 이용해주세요.", "../06Session/LoginForm.jsp", out); //없으면 경고창 -> 로그인 페이지로 넘어감
	return;
}
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>