<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "gugudanProc.jsp" method="post">
	<input type = "radio" name = "sel" value = "1" /> type1 : 세로 구구단 정렬 <br/>
	<input type = "radio" name = "sel" value = "2"/> type2 : 가로 구구단 정렬<br/>
	<input type = "radio" name = "sel" value = "3"/> type3 : 한 단씩 출력 - 단수/열수 입력<br/>
	<input type = "radio" name = "sel" value = "4"/> type4 : 여러 단씩 출력 - 단수/열수 입력 <br/>
	<input type = "text" name = "val"/> 단수/열수 <br/>
	<input type = "submit" value = "선택" />
</form>
</body>
</html>