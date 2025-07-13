<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
BoardDAO dao = new BoardDAO(application); //DAO 생성해서 DB연결
Map<String, Object> param = new HashMap<String, Object>(); //사용자가 입력한 검색 조건을 Map에 저장
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if(searchWord!=null){
	param.put("searchField", searchField);
	param.put("searchWord", searchWord);
}

int totalCount = dao.selectCount(param); //dao가 제공하는 selectCount()메서드를 이용해 게시물 수 확인
List<BoardDTO> boardLists = dao.selectList(param); //dao가 제공하는 selectList()메서드를 이용해 게시물 목록 받기
dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
	<h2>목록보기(List)</h2>
	<!-- 검색폼 -->
	<form method="get">
	<table border="1" width="90%">
		<tr>
			<td align="center">
				<select name ="searchField">
					<option value = "title">제목</option>
					<option value = "content">내용</option>
				</select>
				<input type="text" name="searchWord"/>
				<input type="submit" value="검색하기" />
			</td>
		</tr>
	</table>
	</form>
	<!-- 게시물 목록 테이블 -->
	<table border="1" width="90%">
		<tr>
			<th width = "10%">번호</th>
			<th width = "50%">제목</th>
			<th width = "15%">작성자</th>
			<th width = "10%">조회수</th>
			<th width = "15%">작성일</th>
		</tr>
		<!-- 목록의 내용 -->
		<%
		if(boardLists.isEmpty()){//게시물이 없을 때		
		%>
			<tr>
				<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
			</tr>
		<% 
		}else{//게시물이 있을 때
			int virtualNum = 0; //화면상에서 게시물 번호
			for (BoardDTO dto : boardLists){
				virtualNum = totalCount--; //전체게시물 수에서 1씩 감소
		%>
				<tr align="center">
					<td><%= virtualNum %></td> <!-- 게시물번호 -->
					<td align="left"><a href= "View.jsp?num=<%=dto.getNum()%>"><%=dto.getTitle()%></a></td> <!-- 제목 -->
					<td align="center"><%= dto.getId() %></td> <!-- 작성자 아이디 -->
					<td align="center"><%= dto.getVisitcount() %></td> <!-- 조회수 -->
					<td align="center"><%= dto.getPostdate() %></td> <!-- 작성일 -->
				</tr>
		<% 
			}		
		}
		%>				
	</table>
	<!-- 목록 하단의 글쓰기 버튼 -->
	<table border="1" width="90%">
		<tr>
			<td align="right"><button type="button" onclick="location.href='Write.jsp';">글쓰기</button></td>
		</tr>
	</table>
</body>
</html>