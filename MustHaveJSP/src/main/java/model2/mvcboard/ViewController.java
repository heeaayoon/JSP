package model2.mvcboard;
//servlet/AnnoMapping.java 참조 
//09PagingBoard의 View.jsp와 비교
//상세보기를 위한 서블릿
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//게시물 불러오기
		MVCBoardDAO dao = new MVCBoardDAO(); //DAO 객체를 생성해서 조회수 증가, 상세내용보기 메서드를 호출
		String idx = req.getParameter("idx"); //<a href="../mvcboard/view.do?idx=${row.idx}">넘겨준 파라미터를 idx로 받음
		dao.updateVisitCount(idx); //조회수 1 증가시킨 후,
		MVCBoardDTO dto = dao.selectView(idx); // 게시물 조회 -> DAO의 selectView() 메서드에서 모든 내용을 반환받음
		dao.close();
		
		//줄바꿈 처리
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		//첨부파일 확장자 추출 및 이미지 타입 확인
		String ext = null;
		String fileName = dto.getSfile(); //저장된 파일이름 가져오기
		if(fileName!=null) { //첨부파일이 있는 경우에만, 
			ext = fileName.substring(fileName.lastIndexOf(".")+1); //확장자 추출하기
			//lastIndexOf() : 특정문자열이 마지막으로 나타나는 위치를 반환
			//lastIndexOf()+1 : 특정문자열의 바로다음 글자
			//substring() : lastIndexOf()+1부터 끝까지 잘라내서 반환
		}
		
		String[] mimeStr = {"PNG","png","jpg","gif"};
		List<String> mimeList = Arrays.asList(mimeStr);
		boolean isImage = false;
		if(mimeList.contains(ext)) { //컬렉션에 포함된 내용 중에 위에서 추출한 확장자가 포함되면,
			isImage = true; //isImage 변수를 true로 변경하기
		}
		
		
		//dto 객체와 isImage를 request영역에 저장하고, 뷰(View.jsp)로 포워딩하기
		req.setAttribute("dto", dto); //DB의 모든 정보 담은 dto를 뷰로 넘기기
		req.setAttribute("isImage", isImage); //isImage(T/F)도 뷰로 넘기기
		req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp);
	}
}
