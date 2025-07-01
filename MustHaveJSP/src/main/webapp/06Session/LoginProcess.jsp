<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String userId = request.getParameter("user_id");
String userPwd = request.getParameter("user_pw");

String sqldriver = application.getInitParameter("MySQLDriver");
String sqlurl = application.getInitParameter("MySQLURL");
String sqlid = application.getInitParameter("MySQLId");
String sqlpwd = application.getInitParameter("MySQLPwd");

MemberDAO dao = new MemberDAO(sqldriver, sqlurl, sqlid, sqlpwd);
MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);
dao.close();

if(memberDTO.getId()!=null){
	session.setAttribute("UserId", memberDTO.getId());
	session.setAttribute("UserName", memberDTO.getName());
	response.sendRedirect("LoginForm.jsp");
}else{
	request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
	request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
}
%>