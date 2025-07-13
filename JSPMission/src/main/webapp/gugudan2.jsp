<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 2단에서 9단까지 수평으로 출력 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
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
	<table>
		<tr class="title">
			<%
			for(int i = 2;i<=9;i++){
				out.println("<td>"+i+"단"+"</td>");
			}
			%>
		</tr>
		<% 
		for(int j=1;j<=9;j++){
			out.println("<tr>");
			for(int i = 2;i<=9;i++){
				out.println("<td>"+i+"*"+j+"="+i*j+"</td>");
			}
			out.println("</tr>");
		}
		%>
	</table>

</body>
</html>