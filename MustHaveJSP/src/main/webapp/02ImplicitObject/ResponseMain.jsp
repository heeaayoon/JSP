<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1.로그인 폼</h2>
	<%
	String loginErr = request.getParameter("loginErr");
	if(loginErr != null) out.print("로그인 실패");
	%>
	<form action = "./ResponseLogin.jsp" method="post">
		아이디 : <input type="text" name ="user_id"/><br/>
		비밀번호 : <input type="password" name ="user_pwd"/><br/>
		<input type="submit" value="로그인"/>
	</form>
	
	<h2>2. http 응답 헤더 설정하기</h2>
	<form action = "./ResponseHeader.jsp" method="get">
		날짜 형식 :<input type = "text" name ="add_date" value="2025-05-28 09:00"/><br>
		숫자 형식 :<input type = "text" name ="add_int" value="8282"/><br>
		문자 형식 :<input type = "text" name ="add_str" value="홍길동"/><br>
		<input type = "submit" value="응답헤더 설정&변경"/><br>
	</form>
</body>
</html>