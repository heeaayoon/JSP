<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 2단에서 9단까지 수직으로 출력 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
</head>
<body>
<%
	for(int i=2;i<=9;i++){
		out.println("<h2>"+i+"단입니다.</h2>");
		for(int j=1;j<=9;j++){
			out.println(i+"*"+j+"="+i*j+"<br/>");
		}
	}
%> 
</body>
</html>