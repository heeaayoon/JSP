<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>숫자 포맷 설정</h4>
	<c:set var = "number1" value = "12345"/>
	콤마 O : <fmt:formatNumber value = "${number1 }"/><br/>
	콤마 X : <fmt:formatNumber value = "${number1}" groupingUsed="false"/><br/>
	<fmt:formatNumber value  = "${number1 }" type = "currency" var = "printNum1"/>
	통화기호 : ${printNum1 }<br/>
	<fmt:formatNumber value  = "0.03" type = "percent" var = "printNum2"/>
	퍼센트 : ${printNum2 }<br/>
	
	<h4>문자열을 숫자로 변경</h4>
	<c:set var = "number2" value = "12,345.51"/>
	<fmt:parseNumber value = "${number2}" pattern = "00,000.00" var ="printNum3"/>
	소수점까지 : ${printNum3}<br/>
	<fmt:parseNumber value = "${number2}" integerOnly="true" var ="printNum4"/>
	정수부분만 : ${printNum4}<br/>
</body>
</html>