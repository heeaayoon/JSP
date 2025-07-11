<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %> 
<%
//일련번호 얻기
String num = request.getParameter("num");

BoardDTO dto = new BoardDTO(); //DTO 객체 생성
BoardDAO dao = new BoardDAO(application); //DAO 객체 생성
dto = dao.selectView(num); //선택한 일련번호에 해당하는 게시물 상세보기

//로그인된 사용자 ID 얻기
String sessionId = session.getAttribute("UserId").toString();

int delResult = 0;

if(sessionId.equals(dto.getId())){ //작성자가 로그인한 사람과 동일한지 확인
	System.out.println("로그인 : "+ session.getAttribute("UserId").toString());
	System.out.println("작성자 : "+ dto.getId());
	dto.setNum(num);
	delResult = dao.deletePost(dto); //삭제
	dao.close();
	
	//성공/실패 처리
	if(delResult ==1){
		JSFunction.alertLocation("삭제되었습니다.", "List.jsp", out);
	}else{
		JSFunction.alertBack("삭제에 실패했습니다.", out);
	}	
}else{
	JSFunction.alertBack("본인만 삭제할 수 있습니다.", out);
	
	return;
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