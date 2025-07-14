<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>폼값 전송하기</h2>
	<form name = "frm" method="post" action = "FormResult.jsp">
		이름 : <input type="text" name = "name" /><br/>
		성별 : <input type="radio" name = "gender" value ="Man" />남자
		 	  <input type="radio" name = "gender" value ="Woman" />여자
		학력 :
			<select name = "grade">
				<option value = "ele">초등학생</option>
				<option value = "mid">중학생</option>
				<option value = "high">고등학생</option>
				<option value = "uni">대학생</option>
			</select><br/>
		관심사항 :
			<input type="checkbox" name = "iter" value = "pol"/>정치
			<input type="checkbox" name = "iter" value = "eco"/>경제
			<input type="checkbox" name = "iter" value = "ent"/>연예
			<input type="checkbox" name = "iter" value = "spo"/>운동<br/>
		<input type="submit" value = "전송하기"/>
	</form>
</body>
</html>