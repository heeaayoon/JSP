<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String rgba = "Red Green,Blue,Black=yellow";
	%>
	<h4>JSTL의 forTokens 태그 사용</h4>
	<c:set var = "c" value = "<%= rgba %>"/>
	<c:forTokens items="${c}" delims=", =" var = "color">
		<span style = "color : ${color};">${color}</span><br/>
	</c:forTokens>
</body>
</html>