<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원추가(excuteUpdate() 사용)</h2>
	<form action = "ExeUpdateMember.jsp" method = "post">
	아이디 : <input type ="text" name ="id"/>
	<br/>
	패스워드 : <input type = "text" name = "pw"/>
	<br/>
	이름 : <input type = "text" name ="name"/>
	<br/>
	<input type ="submit" value="입력하기"/>
	</form>
	
	<form action = "./ExeQuery.jsp" >
		<input type ="submit" value="리스트 보기"/>
	</form>
</body>
</html>