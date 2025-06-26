<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
	<h2>회원목록 조회 테스트(executeQuery() 사용)</h2>
	<%
	//DB에 연결
	JDBConnect jdbc = new JDBConnect();
	
	//쿼리문 생성
	String sql = "select id, pass, name, regidate from member";
	Connection con = jdbc.getCon();
	Statement smt = con.createStatement();
	
	//쿼리 수행
	ResultSet rs = smt.executeQuery(sql);
	
	//결과확인(웹 페이지에 출력)
	while(rs.next()){
		String id = rs.getString(1);
		String pw = rs.getString(2);
		String name = rs.getString("name");
		java.sql.Date regidate = rs.getDate("regidate");
		
		out.println(String.format("%s %s %s %s", id, pw, name, regidate)+"<br/>");		
	}
	
	
	//연결닫기
	rs.close();
	smt.close();
	jdbc.close();
	%>
	
	<form action = "./ExeUpdateFormMember.jsp" >
		<input type = "submit" value = "입력form으로 돌아가기"> 
	</form>

</body>
</html>