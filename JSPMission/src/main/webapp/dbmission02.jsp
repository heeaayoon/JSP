<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String code = request.getParameter("code");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");

PreparedStatement psmt = null;
if (code != null) {
	psmt = con.prepareStatement("select * from city where countrycode = ?");
	psmt.setString(1, code);
} else {
	psmt = con.prepareStatement("select * from city");
}

ResultSet rs = psmt.executeQuery();

out.print("Result<br/>");
out.print("도시이름 "+" 인구<br/>");
while(rs.next()){
	out.print(rs.getString("Name") + ", ");
	out.print(rs.getString("Population"));
	out.print("<br/>");
}

rs.close();
psmt.close();
%>