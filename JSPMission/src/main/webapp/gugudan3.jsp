<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 파라미터 val에 입력된 단만 출력 -->
<!-- 파라미터가 없으면 default로 2단 출력 -->
<%
String val = request.getParameter("val");
int dan;
if (val == null)	dan = 2;
else				dan = Integer.parseInt(val);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
</head>
<body>
	<h2><%= dan %>단 입니다.</h2>
	<%
	for (int i=1;i<=9;i++){
		out.println(dan+"*"+i+"="+(dan*i)+"<br/>");
	}
	%>
</body>
</html>