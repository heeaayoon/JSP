<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요. (City)
// 파라미터 설정
String snum = request.getParameter("num");

// 데이터베이스 드라이버 로딩
Class.forName("com.mysql.cj.jdbc.Driver");
// 연결 객체 생성
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");
// 질의 객체 생성
PreparedStatement psmt = null;
if (snum != null) {
	Integer num = Integer.parseInt(snum);	
	psmt = con.prepareStatement("select * from city where population > ?");
	psmt.setInt(1, num);
} else {
	psmt = con.prepareStatement("select * from city");
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
con.close();
%>