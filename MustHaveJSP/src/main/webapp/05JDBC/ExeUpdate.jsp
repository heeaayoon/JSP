<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원추가 테스트 (excuteUpdate() 사용)</h2>
	
	<%
	//DB에 연결
	JDBConnect jdbc = new JDBConnect();
	
	//테스트용 입력값 준비
	String id = "test2";
	String pass = "2222";
	String name = "테스트2회원";
	
	//쿼리문 생성
	String sql = "Insert into member(id, pass, name) values(?,?,?)";
	Connection con = jdbc.getCon();
	PreparedStatement psmt = con.prepareStatement(sql);
	//jdbc.psmt = jdbc.con.preparedStatement(sql);
	psmt.setString(1,id);
	psmt.setString(2,pass);
	psmt.setString(3,name);
	
	//쿼리 수행
	int intResult = psmt.executeUpdate();
	out.println(intResult+"행이 입력되었습니다");
	
	//연결닫기
	psmt.close();
	jdbc.close();
	%>
</body>
</html>