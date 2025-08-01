<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="common.Person"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>List 컬렉션 사용하기</h4>
	<%
	LinkedList<Person> lList = new LinkedList<Person>();
	lList.add(new Person("맹사성", 55));
	lList.add(new Person("장영실", 60));
	lList.add(new Person("신숙주", 54));
	%>
	<c:set var = "lists" value = "<%= lList %>"/>
	<c:forEach items = "${lists}" var = "list">
		<li>이름 : ${ list.name }, 나이 : ${ list.age }</li>
	</c:forEach>
	
	<h4>Map 컬렉션 이용하기</h4>
	<%
	Map<String, Person> maps = new HashMap<String, Person>();
	maps.put("1st",new Person("맹사성", 55));
	maps.put("2nd",new Person("장영실", 60));
	maps.put("3rd",new Person("신숙주", 54));
	%>
	<c:set var = "maps" value = "<%= maps %>"/>
	<c:forEach items = "${maps}" var = "map">
		<li>key => ${map.key} <br/>
			value => 이름 : ${ map.value.name }, 나이 : ${ map.value.age }</li>
	</c:forEach>
</body>
</html>