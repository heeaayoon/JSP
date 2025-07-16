<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function validateForm(form){
		if(form.title.value==""){
			alert("제목을 입력하시오.");
			form.title.focus();
			return false;
		}
		if(form.ofile.value==""){
			alert("첨부파일은 필수 입력입니다.");
			return false;
		}
	}
</script>
<body>
	<h3>파일 업로드</h3>
	<span style="color:red;">${ errorMessage }</span>
	<!-- multipart/form-data : form 내부 태그들을 name 기준으로 나눔(단,name 속성이 같은("cate") 체크박스는, 체크된 것들만 각각 독립된 파트가 됨)
								-> 분할한 파트마다 Content-Disposition(name, 원본파일명)과 Content-Type(데이터 타입)이라는 꼬리표를 붙이기
		 왜 분할하나요? '텍스트'와 '파일(바이너리 데이터)'이라는 완전히 다른 종류의 데이터를 하나의 요청에 섞어서 보낼 때, 
		 서로를 오염시키지 않고 서버가 명확하게 구분할 수 있도록 하기 위함
	 -->
	<form name = "fileForm" method="post" enctype="multipart/form-data"
		action ="UploadProcess.do" onsubmit="return validateForm(this);">
		제목 : <input type="text" name = "title" /><br/>
		카테고리(선택사항) :
			<input type="checkbox" name = "cate" value = "사진" checked />사진
			<input type="checkbox" name = "cate" value = "과제" />과제
			<input type="checkbox" name = "cate" value = "워드" />워드
			<input type="checkbox" name = "cate" value = "음원" />음원<br/>
		첨부파일 : <input type="file" name = "ofile"/><br/>
		<input type = "submit" value="전송하기">
	</form>
	<button type = "button"
			onclick="location.href='FileList.jsp'">목록 보기</button>
	<!-- 이 버튼을 클릭하면 FileList.jsp로 넘어가도록 수정
			onclick="location.href='FileList.jsp'"
			location.href: 현재 브라우저의 주소창
			='FileList.jsp' : 현재브라우저의 주소창을 FileList.jsp로 바꿔라
	 -->
</body>
</html>