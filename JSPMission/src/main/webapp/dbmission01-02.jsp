<%@page import="common.JDBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 파라미터 설정
String snum = request.getParameter("num");

JDBConnect jdbc = new JDBConnect();

// 질의 객체 생성
PreparedStatement psmt = null;
if (snum != null) {
	Integer num = Integer.parseInt(snum);	
	psmt = jdbc.getCon().prepareStatement("select * from city where population > ?");
	psmt.setInt(1, num);
} else {
	psmt = jdbc.getCon().prepareStatement("select * from city");
}
	// 질의 실행
ResultSet rs = psmt.executeQuery();

out.println("Result</br>");
// 커서 프로세싱을 이용한 출력
while(rs.next()) {
	out.print(rs.getString("ID") + ", "); 
	out.print(rs.getString("Name") + ", ");
	out.print(rs.getString("CountryCode") + ", ");
	out.print(rs.getString("District") + ", ");
	out.print(rs.getString("Population"));
	out.println("<br/>");
}
// 리소스 해제
rs.close();
psmt.close();
jdbc.close();
%>