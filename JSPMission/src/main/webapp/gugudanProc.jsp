<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String sel = request.getParameter("sel");
String val = request.getParameter("val");

String url = "gugudan"+sel+".jsp";
if(sel.equals("3")||sel.equals("4")){
	url+="?val="+val;
}
request.getRequestDispatcher(url).forward(request, response);
%>