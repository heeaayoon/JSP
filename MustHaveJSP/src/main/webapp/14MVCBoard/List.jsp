<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 서블릿(ListController.java)에서 처리한 내용을 출력할 뷰 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<style>a{text-decoration: none;}</style>
</head>
<body>
	<h2>파일 첨부형 게시판 - 목록보기</h2>
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
				<th width = "*">제목</th>
				<th width = "15%">작성자</th>
				<th width = "10%">조회수</th>
				<th width = "15%">작성일</th>
				<th width = "8%">첨부</th>
			</tr>
			<!-- 목록의 내용 -->
			<c:choose>
				<c:when test="${empty boardLists}"> <!-- 게시물이 없을 때 -->
					<tr>
						<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise> <!-- 게시물이 있을 때 -->
					<c:forEach items="${boardLists}" var = "row" varStatus="loop">
						<tr align="center">
							<td>${map.totalCount-(((map.pageNum-1)*map.pageSize)+loop.index)}</td> <!-- 게시물번호 -->
							<td><a href="../mvcboard.view.do?idx=${row.idx}">${row.title}</a></td> <!-- 제목 -->
							<td>${row.name}</td> <!-- 작성자 아이디 -->
							<td>${row.visitcount}</td> <!-- 조회수 -->
							<td>${row.postdate}</td> <!-- 작성일 -->
							<td>첨부파일----여기 수정하기</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>	
		<!-- 목록 하단(페이징 바로가기, 글쓰기) -->
		<table border="1" width="90%">
			<tr align="center">
				<td>${map.pagingImg}</td>
				<td width="100"><button type="button" onclick="location.href='../mvcboard/write.do';">글쓰기</button></td>
			</tr>
		</table>
</body>
</html>