<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 서블릿(ViewController.java)에서 포워딩해서 이동할 뷰 -->
<!-- 09PagingBoard의 View.jsp 참조-->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
</head>
<body>
<h2>파일 첨부형 게시판 - 상세보기(View)</h2>
<table border = "1" width = "90%">
	<colgroup>
		<col width="15%"/><col width="35%"/>
		<col width="15%"/><col width="*"/>
	</colgroup>

	<!-- 게시글 내용 -->
	<tr>
		<td>번호</td><td>${dto.idx}</td>
		<td>작성자</td><td>${dto.name}</td>
	</tr>
	<tr>
		<td>작성일</td><td>${dto.postdate}</td>
		<td>조회수</td><td>${dto.visitcount}</td>
	</tr>
	<tr>
		<td>제목</td><td colspan="3">${dto.title}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3" height="100">${dto.content}
			<c:if test="${not empty dto.ofile and isImage eq true}">
				<br><img src = "../Uploads/${dto.sfile}" style="max-width:100%;" />
			</c:if>
		</td>
	</tr>
	
	<!-- 첨부파일 -->
	<tr>
		<td>첨부파일</td>
		<td>
			<c:if test="${not empty dto.ofile}">${dto.ofile}
				<a href = "../mvcboard/download.do?ofile=${dto.ofile}&sfile=${dto.sfile}&idx=${dto.idx}">
				[다운로드]
				</a>
			</c:if>
		</td>
		<td>다운로드 수</td><td>${dto.downcount}</td>
	</tr>
	
	<!-- 하단메뉴(버튼) -->
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
</body>
</html>