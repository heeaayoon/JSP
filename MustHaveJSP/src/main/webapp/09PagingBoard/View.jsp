<%@page import="model1.board.BoardDTO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String num = request.getParameter("num"); //일련번호 매개변수로 받기

BoardDAO dao = new BoardDAO(application); //DAO 객체를 생성해서 조회수,상세내용보기 메서드를 호출
dao.updateVisitCount(num); //조회수 증가
BoardDTO dto = dao.selectView(num); //게시물 조회
dao.close();

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
	<script>
		function deletePost(){
			var confirmed = confirm("정말로 삭제하시겠습니까?");
			if(confirmed) {
				var form = document.writeFrm;  //이름이 writeFrm인 폼 선택
				form.method = "post"; //전송방식	
				form.action = "DeleteProcess.jsp"; //전송 경로
				form.submit(); //폼값을 전송
			}
		}
	</script>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>회원제 게시판 - 상세보기(View)</h2>
	<form name = "writeFrm">
		<input type = "hidden" name = "num" value = "<%= num %>" /> 
		<table border = "1" width = "90%">
			<tr>
				<td>번호</td>
				<td><%= dto.getNum() %></td>
				<td>작성자</td>
				<td><%= dto.getName() %></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%= dto.getPostdate() %></td>
				<td>조회수</td>
				<td><%= dto.getVisitcount() %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3"><%= dto.getTitle() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3" height="100">
					<%
    				// dto나 dto.getContent()가 null이 아닌지 확인합니다.
   					 if (dto != null && dto.getContent() != null) {
        				// null이 아닐 때만 .replace()를 실행합니다.
        				out.print(dto.getContent().replace("\r\n", "<br/>"));
    				}
    				// 만약 null이면 아무것도 출력하지 않으므로 오류가 발생하지 않습니다.
    				%>
				</td>
			</tr>
			
			<tr>
				<td colspan="4" align="center">
					<%
					if(session.getAttribute("UserId")!= null&&session.getAttribute("UserId").toString().equals(dto.getId())){ 
						//로그인 상태가 유지되어 있고, 로그인아이디와 dto 객체에 저장된 아이디가 일치하는지 확인
					%>
						<button type = "button" 
								onclick="location.href = 'Edit.jsp?num=<%= dto.getNum() %>';">수정하기</button>
						<button type = "button" 
								onclick="deletePost();">삭제하기</button>
					<% 
					}					
					%>	
						<button type = "button"
							onclick="location.href = 'List.jsp';">목록 보기</button>
					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>