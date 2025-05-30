<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 2~9단까지 출력을 하는데, 파라미터에 입력된 개수씩 수평 출력 -->
<%
String val = request.getParameter("val");
int st;
if (val == null)	st = 3;
else				st = Integer.parseInt(val);
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		table {
			border: 1px solid;
			border-collapse: collapse;
		}
		td, tr {
			border: 1px solid;
			padding: 4px 10px;
		}

		.title {
			text-align: center;
			background-color: lightgray;
		}
	</style>
</head>
<body>
<%
for(int i = 2;i<=9;i++){
 	out.println(i+"단");
	if(i==st){
		out.println("</br>");
	}
 	}
out.println("</br>");


for(int i = 1;i<=9;i++){
	for(int j = 2; j<=9;j++){
	out.println(j+"*"+i+"="+(i*j));		
	}
	out.println("</br>");
}
%>




<table>
<tr class = "title">
<%
for(int i = 2;i<=st;i++){
 		out.println("<td>"+i+"단"+"</td>");
 	}
//out.println("</br>");
%>
</tr>

<%

for(int i = 1;i<=9;i++){
	out.println("<tr>");
	for(int j = 2; j<=st;j++){
	out.println("<td>"+j+"*"+i+"="+(i*j)+"</td>");		
	}
	//out.println("</br>");
	out.println("</tr>");
}
%>

</table>
</body>
</html>