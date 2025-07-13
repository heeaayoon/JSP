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
<title>Insert title here</title>
</head>
<body>
	<h2>리스트 조회</h2>
	<table>
	<%
	JDBConnect jdbc = new JDBConnect();
	
	String sql = "select num, title, content, id, postdate from board";
	Connection con = jdbc.getCon();
	Statement smt = con.createStatement();
	ResultSet rs = smt.executeQuery(sql);
	
	while(rs.next()){
		int num = rs.getInt(1);
		String title = rs.getString(2);
		String name = rs.getString(3);
		String id = rs.getString(4);
		java.sql.Date postdate = rs.getDate("postdate");
		
		//out.println(String.format("%d %s %s %s %s", num, title, name, id, postdate)+"<br/>");		
	}
	
	rs.close();
	smt.close();
	jdbc.close();
	%>
		<tr>
			<th>num</th>
			<th>title</th>
			<th>content</th>
			<th>id</th>
			<th>postdate</th>
		</tr>
		<tr>
			<th>num</th>
			<th>title</th>
			<th>content</th>
			<th>id</th>
			<th>postdate</th>
		</tr>
		
	</table>
	
	
	<form action = "./ExeUpdateFormBoard.jsp" >
		<input type = "submit" value = "입력form으로 돌아가기"> 
	</form>

</body>
</html>