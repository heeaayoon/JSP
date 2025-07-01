<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요. (Country.Continent)
//select name, continent
//from country
//where continent = "asia";

String continent = request.getParameter("continent");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");

PreparedStatement psmt = null;
if(continent!= null){
	psmt = con.prepareStatement("select name, continent from country where continent = ?");
	psmt.setString(1,continent);
}else{
	psmt = con.prepareStatement("select name, continent from country");
}

ResultSet rs = psmt.executeQuery();
out.print("Result<br/>");
out.print("국가이름 대륙<br/>");

while(rs.next()){
	out.print(rs.getString("name")+", ");
	out.print(rs.getString("continent"));
	out.print("<br/>");
}

rs.close();
psmt.close();
con.close();
%>