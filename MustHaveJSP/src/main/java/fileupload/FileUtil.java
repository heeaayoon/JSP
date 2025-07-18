package fileupload;
//서블릿에서 사용할 유틸리티 메서드
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {
	//파일 업로드 -> 원래 파일명으로 저장
	public static String uploadFile(HttpServletRequest req, String sDirectory) throws IOException, ServletException{
		Part part = req.getPart("ofile"); //part 객체를 통해 서버로 전송된 파일명 읽어오기 : name="ofile"인 값 받아오기
		String dispositionHeader = part.getHeader("content-disposition"); //part 객체의 헤더값 중 content-disposition 읽어오기
		System.out.println("partHeader : "+ dispositionHeader);
		//partHeader : form-data; name="ofile"; filename="2-2. 넘파이실습_브로드캐스팅.ipynb" -> 콘솔에 출력됨
		String [] phArr = dispositionHeader.split("filename="); //content-disposition 중에서 filename 기준으로 잘라서
		//form-data; name="ofile"; <- filename= -> "2-2. 넘파이실습_브로드캐스팅.ipynb"
		//System.out.println(phArr[1]); //어덯게 잘라졌는지 확인
		String originalFileName = phArr[1].trim().replace("\"", ""); 
		//.trim() : 앞뒤 공백을 제거 
		//.replace("\"", "") : 큰따옴표(") 제거
		//결과적으로 따옴표를 제거한 원래의 파일 이름만 originalFileName에 저장
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory+File.separator+originalFileName); //원본파일명이 있으면 디렉토리에 파일을 저장
			//part.write(...) : part 객체가 가지고 있던 실제 바이너리 데이터를 서버의 물리적인 하드 디스크에 파일로 저장(기록)하라
			//sDirectory+File.separator+originalFileName : sDirectory라는 위치에 originalFileName이라는 이름으로!
			//sDirectory는 서블릿에서 생성되어 uploadFile 메소드를 호출할 때 인자로 전달됨
		}
		return originalFileName;
	}
	
	//파일명 변경 -> 새로운 파일명 붙이기
	public static String renameFile(String sDirectory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		//fileName.lastIndexOf("."): 문자열 fileName에서 마지막으로 나타나는 .의 위치 찾기
		//fileName.substring(...): 찾은 위치부터 문자열 끝까지 자르기
		//System.out.println(ext); //확장자만 남음
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date()); //년월일_시분초 형식의 파일명 생성
		//System.out.println(now); //현재 시간을 년월일_시분초 포맷으로 변환
		String newFileName = now+ext; 
		//System.out.println(); //시간+확장자로 새로운 파일명 생성
		File oldFile = new File(sDirectory + File.separator + fileName); //원래이름+실제 데이터
		File newFile = new File(sDirectory + File.separator + newFileName); //새이름
		oldFile.renameTo(newFile);  //원래이름을 새이름으로 바꾸면서 -> 이제 새 이름이 실제 데이터를 갖게 됨
		
		return newFileName;
	}
	
	
	//멀티 파일 업로드
	public static ArrayList<String> multipleFile(HttpServletRequest req, String sDirectory) throws IOException, ServletException{
		ArrayList<String> listFileName = new ArrayList<String>();
		Collection<Part> parts = req.getParts(); //part 객체를 통해 서버로 전송된 파일명 읽어오기
		for(Part part : parts) {
			if(!part.getName().equals("ofile")) continue;
			
			String partHeader = part.getHeader("content-disposition"); //Part 객체의 헤더값 중 content-disposition 읽어오기
			System.out.println("partHeader : "+ partHeader);
			String [] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"", "");
			if(!originalFileName.isEmpty()) {
				part.write(sDirectory+File.separator+originalFileName); //파일명이 있으면 디렉토리에 파일을 저장
			}
			listFileName.add(originalFileName);
		}
		return listFileName;
	}
	
	//파일을 찾아서 다운로드
	//13FileUpload/Download.jsp 참조
	public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String sfileName, String ofileName) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		try{
			//파일을 찾아서 입력 스트림 생성
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			//한글 파일명 깨짐 방지
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64")==-1) {
				ofileName = new String(ofileName.getBytes("UTF-8"),"ISO-8859-1");
			}else{
				ofileName = new String(ofileName.getBytes("KSC5601"),"ISO-8859-1");
			}
			
			//파일 다운로드용 응답헤더 설정
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\""+ofileName+"\"");
			resp.setHeader("Content-Length", ""+file.length());
			
			//출력 스트림 초기화 -> 서블릿에서는 이 코드가 없어도 오류가 발생하지 않음
			//out.clear();
			
			//response 내장 객체로부터 새로운 출력 스트림 생성
			OutputStream oStream = resp.getOutputStream();
			
			//출력 스트림에 파일 내용 출력
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer=iStream.read(b))>0){
				oStream.write(b,0,readBuffer);
			}
			
			//입출력 스트림 닫음
			iStream.close();
			oStream.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("다운로드 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	//지정한 위치의 파일을 삭제
	public static void deleteFile(HttpServletRequest req, String directory, String filename) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory+File.separator+filename);
		if(file.exists()) {
			file.delete();
		}
	}
}
