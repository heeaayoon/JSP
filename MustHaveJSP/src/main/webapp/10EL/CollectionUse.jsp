<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="common.Person"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>List 컬렉션</h2>
	<%
	List <Object> aList = new ArrayList<Object>();
	aList.add("청해진");
	aList.add(new Person("장보고",28));
	pageContext.setAttribute("Ocean", aList);
	%>
	
	<ul>
		<li>0번째 요소 : ${ Ocean[0] }</li>
		<li>1번째 요소 : ${ Ocean[1].name }</li>
		<li>2번째 요소 : ${ Ocean[2] }</li>
	</ul>
	<h2>Map 컬렉션</h2>
	<%
	Map<String, String> map = new HashMap<String, String>();
	map.put("한글","훈민정음");
	map.put("Eng","English");
	pageContext.setAttribute("King", map);
	%>
	<ul>
		<li>영문 키 : ${ King["Eng"] }, ${ King['Eng'] }, ${ King.Eng } </li>
		<li>한글 키 : ${ King["한글"] }, ${ King['한글'] }, \${ King.한글 } </li> <!-- 한글/기호를 사용할 때는 대괄호 형식으로 사용하는 게 나음 -->
	</ul>
</body>
</html>