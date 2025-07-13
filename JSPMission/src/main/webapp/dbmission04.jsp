<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//국가 코드 또는 국가 명의 일부를 입력 받아서 해당 국가의 도시의 이름과 인구를 검색해서 출력하세요. (City, Count)
//select t.Code, t.name, c.name, c.population
//from city c, country t 
//where c.countrycode=t.code 
//	and (c.countrycode like ? or t.name like ?)

String codeOrName = request.getParameter("codeOrName");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");

PreparedStatement psmt = null;
if(codeOrName!=null){
	psmt = con.prepareStatement("select t.Code, t.name, c.name, c.population from city c, country t where c.countrycode=t.code and (c.countrycode like ? or t.name like ?)");
	psmt.setString(1,"%"+codeOrName+"%");
	psmt.setString(2,"%"+codeOrName+"%");
}else{
	psmt = con.prepareStatement("select t.Code, t.name, c.name, c.population from city c, country t where c.countrycode=t.code");
}

ResultSet rs = psmt.executeQuery();

out.print("Result<br />");
out.print("나라코드 나라이름 도시이름 도시인구<br/>");
while(rs.next()){
	out.print(rs.getString("t.code") + ", "); 
	out.print(rs.getString("t.name") + ", ");
	out.print(rs.getString("c.name") + ", ");
	out.print(rs.getString("c.Population"));
	out.println("<br/>");
}

rs.close();
psmt.close();
con.close();
%>