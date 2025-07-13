<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %> 
<%
//폼값 받기
String title = request.getParameter("title");
String content = request.getParameter("content");

//폼값을 DTO 객체에 저장
BoardDTO dto = new BoardDTO();
dto.setTitle(title);
dto.setContent(content);
dto.setId(session.getAttribute("UserId").toString());
System.out.println("dto 객체 : "+dto);

//DAO 객체를 통해 insertWrite 메소드로 DB에 DTO(사용자에게 폼으로 받은 값) 저장 
BoardDAO dao = new BoardDAO(application);
int iresult = dao.insertWrite(dto);
//int iresult = 0; //더미데이터 만들기
//for(int i =1;i<=100;i++){
//	dto.setTitle(title+"-"+i);
//	iresult = dao.insertWrite(dto);
//}
dao.close();

//성공여부 확인
if(iresult==1){
	response.sendRedirect("List.jsp"); //성공하면 목록페이지로 이동
}else{
	JSFunction.alertBack("글쓰기에 실패하였습니다.", out); //실패하면 알람창 -> 이전페이지(Write.jsp)로 이동
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>