<%@page import="utils.BoardPage"%>
<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
BoardDAO dao = new BoardDAO(application); //DAO 생성해서 DB연결

//사용자가 입력한 검색 조건을 Map(이름표(Key)와 값(Value)을 한 쌍으로 묶어서)에 저장
//전체적인 흐름 : request에서 값을 꺼내와서 param에 집어넣기
Map<String, Object> param = new HashMap<String, Object>(); 
String searchField = request.getParameter("searchField"); 
String searchWord = request.getParameter("searchWord");
if(searchWord!=null){
	param.put("searchField", searchField); //param.put(키,값)
	param.put("searchWord", searchWord);
}
int totalCount = dao.seletCount(param); //dao가 제공하는 selectCount()메서드를 이용해 전체 게시물 수 확인

//페이징 처리

//전체 페이지 수 계산
int pageSize = Integer.parseInt(application.getInitParameter("POST_PER_PAGE")); //페이지당 게시물 수 : 10 -> web.xml에서 설정한 값 가져오기
int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK")); //블록당 페이지 수 : 5 -> web.xml에서 설정한 값 가져오기
int totalPage = (int)Math.ceil((double)totalCount/pageSize); //전체 페이지 수 : (전체 게시물 수/페이지당 게시물 수)

//현재 페이지 확인
int pageNum = 1; //기본값
//request.getParameter() : 반환 타입은 무조건 문자열(String)임
//1. get 방식 : url의 ? 뒤에 있는 이름=값 형태로 파라미터를 받아옴
//2. post 방식 : HTML <form>을 통해 데이터를 숨겨서 전달(로그인) -> getParameter(이름)에 사용하는 이름은 <input> 태그의 name 속성값과 정확히 일치해야 함
String pageTemp = request.getParameter("pageNum"); //현재 웹 페이지의 URL 주소에서 pageNum이라는 이름으로 전달된 값을 문자열(String) 형태로 가져오기
if(pageTemp != null && !pageTemp.equals("")) //전달된 것이 없지 않으면(있으면)
	pageNum = Integer.parseInt(pageTemp); //1(기본값)에서 전달받은 페이지로 수정

//목록에 출력할 게시물 범위 계산
//selectListPage() 메서드에서 필요한 두 값 param에 집어 넣기
//query += " .... limit ?,? ";
//limit [건너뛸 개수], [가져올 개수]
int start = (pageNum-1) * pageSize; 
//ex) 3 페이지 -> 20개 게시물 건너뛰기
//건너뛸 게시물 갯수 : (현재 내 페이지-1)* 페이지 당 게시물 수
 param.put("start", start); //건너뛸 갯수 : 1페이지는 0개 건너뛰기(0-based)
 param.put("pageSize", pageSize); //가져올 갯수 = 페이지당 게시물 수


List<BoardDTO> boardLists = dao.selectListPage(param); //dao가 제공하는 selectListPage()메서드를 이용해 게시물 목록 받기
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
	<h2>목록보기(List) - 현재 페이지 : <%=pageNum %>(전체 : <%= totalPage %>)</h2>
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
		}else{	//게시물이 있을 때
			int virtualNum = 0; //화면상에서 게시물 번호
			int countNum =0;
			for (BoardDTO dto : boardLists){
				//virtualNum = totalCount--; //전체게시물 수에서 1씩 감소 -> 최신순으로 받아오기로 앞서 쿼리문에서 설정함
				virtualNum = totalCount-(((pageNum-1)*pageSize)+countNum++); //게시물 번호 : 전체게시물수 - (((현재페이지 번호-1)*페이지당 게시물 수)+0~9)
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
		<tr align="center">
			<td>
				<%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) %>
				<!-- request.getRequestURI() : 컨텍스트 경로 + 서블릿 경로(http://.../List.jsp)까지만 가져옴  -->
			</td>
			<td><button type="button" onclick="location.href='Write.jsp';">글쓰기</button></td>
		</tr>
	</table>
</body>
</html>