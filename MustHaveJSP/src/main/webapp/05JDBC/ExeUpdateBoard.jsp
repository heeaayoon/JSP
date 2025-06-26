<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>데이터 입력</h2>
	<%
	//DB에 연결
	JDBConnect jdbc = new JDBConnect();
	
	//form에서 입력값 받아오기 
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String id = request.getParameter("id");
	
	//쿼리문 생성
	String sql = "Insert into board(title, content, id) values(?,?,?)";
	Connection con = jdbc.getCon();
	PreparedStatement psmt = con.prepareStatement(sql);
	psmt.setString(1,title);
	psmt.setString(2,content);
	psmt.setString(3,id);
	
	//쿼리 수행
	int intResult = psmt.executeUpdate();
	out.println(intResult+"행이 입력되었습니다");
	
	//연결닫기
	psmt.close();
	jdbc.close();	
	%>
	
	<form action = "./ExeQueryBoard.jsp" >
		<input type ="submit" value="리스트 보기"/>
	</form>

</body>
</html>