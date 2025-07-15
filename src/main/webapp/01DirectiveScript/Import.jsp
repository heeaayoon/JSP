<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - import 속성</title>
</head>
<body>
<%
Date today = new Date(); //외부클래스를 생성하면 자동으로 import 됨
SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd"); //외부 클래스 생성
String todayStr = dataFormat.format(today);
out.println("오늘 날짜 : "+ todayStr); //오늘 날짜를 웹 브라우저에 출력
%>
</body>
</html>