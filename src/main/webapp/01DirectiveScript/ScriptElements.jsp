<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! //선언부 : java파일의 __jspService() 메소드 외부에 필드,메소드가 선언됨
public int add(int num1, int num2){
	return num1+num2;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% //실행되어야 하는 코드 : java파일의 __jspService() 메소드 내부에 메소드가 선언됨
int result = add(10,20);
%>
덧셈결과 1 : <%= //표현식 : 주로 변수의 값을 웹 브라우저 화면에 출력할 때 사용됨
				result %><br/> 
덧셈결과 2 : <%= add(30,40) %>
</body>
</html>