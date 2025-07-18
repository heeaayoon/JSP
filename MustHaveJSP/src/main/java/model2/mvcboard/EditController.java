package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/mvcboard/edit.do")
@MultipartConfig(
		maxFileSize = 1024*1024*1,
		maxRequestSize = 1024*1024*10
		)
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selectView(idx);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/14MVCBoard/Edit.jsp").forward(req, resp); //edit.jsp로 포워딩
	}
	
	//WriteController.java의 doPost() 참조
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String saveDirectory = getServletContext().getRealPath("/Uploads"); //보관장소(saveDirectory) 설정
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(req, saveDirectory); //원래 파일명
		}catch(Exception e) {
			JSFunction.alertBack(resp, "파일 업로드 오류입니다.");
			return;
		}
		
		//수정 내용을 파라미터에서 얻어오기
		String idx = req.getParameter("idx");
		String prevOfile = req.getParameter("prevOfile");
		String prevSfile = req.getParameter("prevSfile");
		
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		//비밀번호는 세션에서 얻어오기
		HttpSession session = req.getSession();
		String pass = (String)session.getAttribute("pass");
		
		MVCBoardDTO dto = new MVCBoardDTO(); //데이터 운반 객체 dto 준비 -> dto에 수정폼값에서 받아온 정보를 저장
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
		
		//원래 파일명과 새로운(저장된) 파일 이름 설정
		if(originalFileName!="") {
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName); //첨부파일이 있을 경우에만, 파일명을 변경
			dto.setOfile(originalFileName); //원래파일명 저장
			dto.setSfile(savedFileName); //저장된 파일명 저장
			
			//기존 파일은 삭제
			FileUtil.deleteFile(req, "/Uploads", prevSfile); 
		}else { //새로운 첨부파일이 없는 경우에는, 기존 이름을 유지
			dto.setOfile(prevOfile); 
			dto.setSfile(prevSfile);
		}
		
		//DB에 수정한 내용을 반영
		MVCBoardDAO dao = new MVCBoardDAO(); //DB와 관련된 모든 실무를 전문적으로 처리하는 dao 객체 준비
		int result = dao.updatePost(dto); //모든 정보가 저장된 dto를 통째로 넘겨주기 -> MyFileDAO의 insertWrite() 메서드가 실행되면서 DB에 정보가 저장됨
		dao.close();
		
		//성공했는지?
		if(result==1) { //수정 성공
			session.removeAttribute("pass"); //session영역에 저장된 비밀번호는 삭제하고
			resp.sendRedirect("../mvcboard/view.do?idx="+idx); //상세보기 뷰로 이동해서 수정된 내용을 확인
		}else { //수정 실패
			JSFunction.alertLocation(resp, "수정에 실패했습니다.", "../mvcboard/view.do?idx="+idx); //비밀번호 검증에 문제가 있는 것이므로 상세보기 뷰로 이동해서 다시 비밀번호 검증을 유도
		}
	}
}
