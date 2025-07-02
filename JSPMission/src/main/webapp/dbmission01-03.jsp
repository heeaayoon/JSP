<%@page import="common.Mission1DTO"%>
<%@page import="java.util.List"%>
<%@page import="common.JDBConnect2"%>
<%@page import="common.JDBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 파라미터 입력받기
String snum = request.getParameter("num");
Integer num = null; //초기값은 null 로 설정

//snum이 null이 아니고 빈 문자열이 아닐 경우에만 숫자로 변환
if(snum != null && !snum.trim().isEmpty()){
	num = Integer.parseInt(snum);
}

//JDBConnect2 불러오기 
JDBConnect2 jdbc = new JDBConnect2();

//JDBConnect2에서 만든 getCityByPopulation 메서드 불러오기
List<Mission1DTO> list = jdbc.getCityByPopulation(num);

//확장 for문 이용해서 출력 
for(Mission1DTO dto : list){
	out.print(dto.countrycode+", ");
	out.print(dto.population+"<br/>");
}
%>