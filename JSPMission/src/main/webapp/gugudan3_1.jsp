<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
int x = Integer.parseInt(request.getParameter("val")); //request.getParameter("val")이 null 인 상황에서 오류가 발생함 -> 오류 처리를 미리 해줘야 함
if (x==0) x = 2;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
</head>
<body>
	<h2><%= x %>단입니다.</h2>
</body>
</html>