<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//넓이를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로 검색해서 출력하세요. (Country.SurfaceArea)
//select name, surfacearea
//from country
//where SurfaceArea<10000;

String sarea = request.getParameter("area");

Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");
PreparedStatement psmt = null;
if(sarea!=null){
	Integer area = Integer.parseInt(sarea);
	psmt = con.prepareStatement("select name, surfacearea from country where SurfaceArea<?");
	psmt.setInt(1, area);
}else{
	psmt = con.prepareStatement("select name, surfacearea from country");
}

//질의 실행
ResultSet rs = psmt.executeQuery();

out.println("Result</br>");
//커서 프로세싱을 이용한 출력
while(rs.next()) {
	out.print(rs.getString("name") + ", ");
	out.print(rs.getString("surfacearea"));
	out.println("<br/>");
}
//리소스 해제
rs.close();
psmt.close();
con.close();

%>