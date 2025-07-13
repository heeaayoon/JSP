package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl ) {
		String pagingStr = "";
		
		//전체 페이지 수 계산 = 전체 게시물 수 / 페이지 당 게시물 수
		//Math.ceil : 올림처리 (반올림하면 마지막 페이지의 게시물을 볼 수 없음)
		int totalPages = (int) (Math.ceil(((double)totalCount/pageSize)));
		
		//'이전 페이지 블록 바로가기' 출력
		int pageTemp = (((pageNum-1)/blockPage)*blockPage)+1; 
		//blockPage : 블록 안에 몇 페이지? 5로 고정
		//pageTemp : 현재 페이지가 속한 블록의 가장 첫번째 페이지 (1,6,11,16...)
		if(pageTemp != 1) { //pageTemp가 1이 아닌 경우에만 실행됨
			pagingStr += "<a href='"+reqUrl+"?pageNum=1'>[첫페이지]</a>"; //첫페이지로 이동하는 하이퍼링크 태그
			pagingStr += "&nbsp;"; //&nbsp; 은 공백을 화면에 표시해줌
			pagingStr += "<a href='"+reqUrl+"?pageNum=" +(pageTemp-1)+"'>[이전 블록]</a>"; //이전블록의 가장 마지막 페이지로 이동
		}
		
		//각 페이지 번호 출력
		int blockCount = 1;
		while(blockCount <= blockPage &&pageTemp<=totalPages) { //blockCount가 1~5까지 while문 돌기(단, 현재 페이지는 총 페이지를 넘을 수 없음)
			if(pageTemp==pageNum) {
				pagingStr += "&nbsp;"  + pageTemp + "&nbsp;"; //현재 페이지에는 하이퍼링크를 걸지 않음
			}else {
				pagingStr += "&nbsp;<a href='"+reqUrl+"?pageNum=" +pageTemp+"'>"+pageTemp+"</a>&nbsp;"; 
				//현재페이지가 아닌 경우에는 현재페이지가 속한 블록의 가장 첫번째 페이지부터~ 그려냄
			}
			pageTemp++;
			blockCount++;
		}
		
		//'다음 페이지 블록 바로가기' 출력 
		// 각 페이지 번호를 출력하면서(while문 돌면서) pageTemp=1 -> [12345] 출력 -> pageTemp=6이 됨
		if(pageTemp <= totalPages) { //현재 페이지가 총 페이지를 넘지 않을 동안에만 실행됨
			pagingStr += "<a href='"+reqUrl+"?pageNum="+pageTemp+"'>[다음 블록]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='"+reqUrl+"?pageNum=" + totalPages +"'>[마지막 페이지]</a>";
		}
		
		return pagingStr;
	}
}
