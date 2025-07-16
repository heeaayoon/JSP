package fileupload;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//UploadProcess.java 서블릿 코드가 multipart/form-data로 받아온 데이터와 어떤 연관이 있는지
// /13FileUpload/UploadProcess.do 주소가 실행되면 이 서블렛이 작동함
@WebServlet("/13FileUpload/UploadProcess.do")
@MultipartConfig(
		maxFileSize = 1024*1024*1,
		maxRequestSize = 1024*1024*10
		)
public class UploadProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//파일 업로드 처리 메서드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String saveDirectory = getServletContext().getRealPath("/Uploads"); //보관장소(sDirectory) 설정
			String originalFileName = FileUtil.uploadFile(req, saveDirectory); //원래 파일명
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName); //새로운 파일명
			insertMyFile(req,originalFileName,savedFileName); //form에 제출한 내용, 원래파일명, 새 파일명을 DB에 저장할 수 있도록 파라미터로 넘기기
			resp.sendRedirect("FileList.jsp"); //sendRedirect : 다른 주소로 이동
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage","파일 업로드 오류");
			req.getRequestDispatcher("FileUploadMain.jsp").forward(req, resp);
		}
	}

	//데이터베이스에 기록하는 메서드
	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
		//Form에 사용자가 입력하거나 선택한 내용 중 name이 title, cate인 부분만 서버(서블릿)에서 받아오기
		String title = req.getParameter("title");
		String [] cateArray = req.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		if(cateArray==null) {
			cateBuf.append("선택한 항목 없음");
		}else {
			for(String s:cateArray) cateBuf.append(s+", ");
		}
		
		MyFileDTO dto = new MyFileDTO(); //데이터 운반 객체 dto 준비
		//dto에 정보를 저장
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
		MyFileDAO dao = new MyFileDAO(); //DB와 관련된 모든 실무를 전문적으로 처리하는 dao 객체 준비
		dao.insertFile(dto); //모든 정보가 저장된 dto를 통째로 넘겨주기 -> MyFileDAO의 insertFile() 메서드가 실행되면서 DB에 정보가 저장됨
		dao.close();
	}	
}
