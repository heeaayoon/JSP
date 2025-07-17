package model2.mvcboard;
//09PagingBoard의 List.jsp와 비교
//서블릿
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MVCBoardDAO dao = new MVCBoardDAO();
		
		//뷰에 전달할 매개변수 저장용 맵 생성
		Map<String, Object> map = new HashMap<String, Object>();
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		if(searchWord!=null){ //쿼리스트링으로 전달받은 매개변수 중에서 검색어가 있다면 map에 저장
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		int totalCount = dao.selectCount(map); //dao가 제공하는 selectCount()메서드를 이용해 전체 게시물 수 확인
		
		//페이징 처리 시작
		ServletContext application = getServletContext(); //application 객체 생성 왜? getgetInitParameter() 메소드를 사용하기 위해서
		int pageSize = Integer.parseInt(application.getInitParameter("POST_PER_PAGE")); //페이지당 게시물 수 : 10 -> web.xml에서 설정한 값 가져오기
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK")); //블록당 페이지 수 : 5 -> web.xml에서 설정한 값 가져오기
		
		//현재 페이지 확인
		int pageNum = 1; //기본값 : 1페이지
		String pageTemp = req.getParameter("pageNum"); //현재 웹 페이지의 URL 주소에서 pageNum이라는 이름으로 전달된 값을 문자열(String) 형태로 가져오기
		if(pageTemp != null && !pageTemp.equals("")) //전달된 것이 없지 않으면(있으면)
			pageNum = Integer.parseInt(pageTemp); //1(기본값)에서 전달받은 페이지로 수정
		//목록에 출력할 게시물의 범위를 계산
		int start = (pageNum-1) * pageSize; //건너뛸 게시물 갯수 계산
		map.put("start", start); //건너뛸 갯수
		map.put("pageSize", pageSize); //가져올 게시물 갯수
		//페이징 처리 끝
		
		List<MVCBoardDTO> boardLists = dao.selectListPage(map); //dao가 제공하는 selectListPage()메서드를 이용/ 검색어와 게시물 범위를 담은 map을 파라미터로 주고 게시물 목록 받기
		dao.close();
		
		//뷰에 전달할 매개변수 추가
		//Utils/BoardPage.java 사용 -> 페이징 영역 <html>과 관련
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvcboard/list.do");
		map.put("pagingImg", pagingImg); //페이지 바로가기 영역의 html코드 추가
		map.put("totalCount", totalCount); //총 게시물 갯수 추가
		map.put("pageSize", pageSize); //페이지당 게시물 수 추가
		map.put("pageNum", pageNum); //페이지 번호 추가
		
		//뷰로 전달할 데이터를 request영역에 저장 후 List.jsp로 포워딩
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req, resp);
	}
}
