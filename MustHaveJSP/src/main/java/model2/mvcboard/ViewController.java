package model2.mvcboard;
//servlet/AnnoMapping.java 참조 
//09PagingBoard의 View.jsp와 비교
//상세보기를 위한 서블릿
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fileupload.FileUtil;
import fileupload.MyFileDAO;
import fileupload.MyFileDTO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;
import utils.JSFunction;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//게시물 불러오기
		MVCBoardDAO dao = new MVCBoardDAO(); //DAO 객체를 생성해서 조회수 증가, 상세내용보기 메서드를 호출
		String idx = req.getParameter("idx");
		dao.updateVisitCount(idx); //조회수 1 증가
		MVCBoardDTO dto = dao.selectView(idx); //게시물 조회
		dao.close();
		
		//줄바꿈 처리
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		//첨부파일 확장자 추출 및 이미지 타입 확인
		String ext = null;
		String fileName = dto.getSfile();
		if(fileName!=null) {
			ext = fileName.substring(fileName.lastIndexOf(".")+1); //확장자 추출하기
		}
		
		String[] mimeStr = {"PNG","png","jpg","gif"};
		List<String> mimeList = Arrays.asList(mimeStr);
		boolean isImage = false;
		if(mimeList.contains(ext)) { //컬렉션에 포함된 확장자를 가진 이미지면
			isImage = true; //isImage 변수를 true로 변경하기
		}
		
		//dto 객체와 isImage를 request영역에 저장하고 뷰(View.jsp)로 포워딩하기
		req.setAttribute("dto", dto);
		req.setAttribute("isImage", isImage);
		req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp);
	}
}
