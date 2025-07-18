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
		<td>번호</td><td>${dto.idx}</td> <!-- 리퀘스트 영역에 저장한 dto 사용 -->
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
		<td colspan="3" height="100">${dto.content}${ dto.ofile }${ dto.sfile }
			<c:if test="${not empty dto.ofile and isImage eq true}"> <!-- dto에 ofile이 있고, 그 파일이 isImage==true인 경우에만 -->
				<br><img src = "../Uploads/${dto.sfile}" style="max-width:100%;" /> <!-- <img>태그가 실행됨 -->
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
			<button type = "button" 
					onclick="location.href = '../mvcboard/pass.do?mode=edit&idx=${param.idx}';">수정하기</button>
			<button type = "button" 
					onclick="location.href = '../mvcboard/pass.do?mode=delete&idx=${param.idx}';">삭제하기</button>
			<button type = "button"
					onclick="location.href = '../mvcboard/list.do';">목록 보기</button>
		</td>
	</tr>

</body>
</html>