<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 2~9단까지 출력을 하는데, 파라미터에 입력된 개수의 단을 수평 출력 -->
<%
String val = request.getParameter("val");
int dan;
if (val == null)	dan = 3;
else				dan = Integer.parseInt(val);
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
<table>
	<%
// 	for(int i = 2;i<=dan;i++){
// 		out.println("<tr>");
// 		out.println("<td>"+i+"단"+"</td>");
// 		out.println("<td>"+i+"*"+1+"="+(i*1)+"<td/>");
// 	}
// 	out.println("</tr>");
	%>


<%
// for(int j=1; j<=9;j++){
// 	out.println("<tr>");
// 	for(int i=2;i<=dan;i++){
// 		out.println("<td>"+i+"*"+j+"="+(i*j)+"<td/>");
// 	}
// 	out.println("</tr>");
// }
%>
</table>

<%
for(int i = 2;i<=dan;i++){
 		out.println(i+"단\n"+"</t>");
 	}
 		out.println(i+"*"+1+"="+(i*1));
		out.println("</br>");

%>



</body>
</html>