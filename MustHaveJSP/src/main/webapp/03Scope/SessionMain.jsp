<%@ page import = "common.Person" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<String> lists = new ArrayList<String>();
lists.add("리스트");
lists.add("컬렉션");
session.setAttribute("lists", lists);


ArrayList<Person> pList = new ArrayList<Person>();
pList.add(new Person("a",12));
pList.add(new Person("b",20));
pList.add(new Person("c",32));
session.setAttribute("pList", pList);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session 영역</title>
</head>
<body>
	<h2>페이지 이동 후 session 영역의 속성 읽기</h2>
	<a href = "SessionLocation.jsp">SessionLocation.jsp 바로가기</a>
</body>
</html>