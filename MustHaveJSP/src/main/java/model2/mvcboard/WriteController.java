package model2.mvcboard;
//fileupload/UploadProcess.java 참조 
//서블릿
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fileupload.FileUtil;
import fileupload.MyFileDAO;
import fileupload.MyFileDTO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;
import utils.JSFunction;

public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//글쓰기 폼으로 진입하기 위한 구간
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/14MVCBoard/Write.jsp").forward(req, resp);
	}
	
	//폼값을 받아 DB처리를 하는 구간
	//fileupload/UploadProcess.java -> 파일업로드 처리 메서드 참조
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDirectory = getServletContext().getRealPath("/Uploads"); //보관장소(sDirectory) 설정
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(req, saveDirectory); //원래 파일명
		}catch(Exception e) {
			JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", "../mvcboard/write.do");
			return;
		}
		
		MVCBoardDTO dto = new MVCBoardDTO(); //데이터 운반 객체 dto 준비 -> dto에 폼값에서 받아온 정보를 저장
		dto.setName(req.getParameter("name"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setPass(req.getParameter("pass"));
		//원래 파일명과 새로운(저장된) 파일 이름 설정
		if(originalFileName!="") {
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName); //첨부파일이 있을 경우에만, 파일명을 변경
			dto.setOfile(originalFileName); //원래파일명 저장
			dto.setSfile(savedFileName); //저장된 파일명 저장
		}
		
		MVCBoardDAO dao = new MVCBoardDAO(); //DB와 관련된 모든 실무를 전문적으로 처리하는 dao 객체 준비
		int result = dao.insertWrite(dto); //모든 정보가 저장된 dto를 통째로 넘겨주기 -> MyFileDAO의 insertWrite() 메서드가 실행되면서 DB에 정보가 저장됨
		dao.close();
		
		//성공했는지?
		if(result==1) { //글쓰기 성공
			resp.sendRedirect("../mvcboard/list.do"); //목록으로 이동
		}else { //글쓰기 실패
			JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", "../mvcboard/write.do");
		}
		
	}
}
