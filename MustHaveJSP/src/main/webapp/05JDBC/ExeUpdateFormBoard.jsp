<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>DB에 board를 입력</h2>
	<form action = "ExeUpdateBoard.jsp" method = "post">
	Title : <input type ="text" name ="title"/>
	<br/>
	Content : <input type = "text" name = "content"/>
	<br/>
	Id : <input type = "text" name ="id"/>
	<br/>
	<input type ="submit" value="입력하기"/>
	</form>
	
	<form action = "./ExeQueryBoard.jsp" >
		<input type ="submit" value="리스트 보기"/>
	</form>

</body>
</html>