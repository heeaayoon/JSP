<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page import = "common.Person" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>application영역의 공유</h2>
	<%
	Map<String, Person> maps = new HashMap<>();
	maps.put("actor1", new Person("이수일", 30));
	maps.put("actor2", new Person("심", 28));
	application.setAttribute("maps", maps);
	%>
	app 영역에 저장되었습니다.
</body>
</html>