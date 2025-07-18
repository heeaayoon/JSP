package model2.mvcboard;
//BoardDAO.java 참조
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect {
	public MVCBoardDAO() {
		super();
	}
	
	//검색 조건에 맞는 게시물의 수를 반환하는 메서드
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		Statement stmt = null;
		ResultSet rs = null;
		//쿼리문
		String query =  "select count(*) from mvcboard ";
		if(map.get("searchWord") != null){
			query += " WHERE " + map.get("searchField")
			 + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		try{
			stmt= getCon().createStatement(); //쿼리문 생성
			rs = stmt.executeQuery(query); //쿼리문 실행
			rs.next();
			totalCount = rs.getInt(1); //ResultSet 객체의 1번 인덱스의 결과를 정수로 추출 
		}catch (Exception e) {
			System.out.println("게시물 수 카운트 중 예외 발생");
			e.printStackTrace();
		}		
		return totalCount;
	}
	
	//검색 조건에 맞는 게시물의 목록을 반환하는 메서드
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map){
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//쿼리문
		String query =  "select * from mvcboard ";
		if(map.get("searchWord") != null){ //Map 컬렉션에 "searchWord" 키로 저장된 값이 있는 경우에만 -> where절 추가
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query+= " ORDER BY idx DESC limit ?,? ";//최근게시물이 상단에 출력되도록 내림차순 정렬
		//System.out.println("쿼리문 : "+query); //쿼리문 테스트용
		
		try{
			psmt= getCon().prepareStatement(query); //동적 쿼리문 생성
			psmt.setInt(1, (int)map.get("start"));
			psmt.setInt(2, (int)map.get("pageSize"));
			rs = psmt.executeQuery();
			
			//반환된 게시물 목록을 List 컬렉션에 추가
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
			
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
		}catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board; //게시물 목록 반환 
	}
	
	//게시글 데이터를 받아 DB에 추가하는 메서드 
	//fileupload/MyFileDAO.java 참고하기
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			String query =  "INSERT INTO mvcboard (name, title, content, ofile, sfile, pass) VALUES (?, ?, ?, ?, ?, ?)";
			psmt = getCon().prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 입력중 예외발생");
			e.printStackTrace();
		}finally {
			if(psmt!=null)
				try {
					psmt.close();
				} catch (SQLException e) {
					System.out.println("Insert 중 예외발생2");
					e.printStackTrace();
				}
		}
		return result;
	}
	
	//선택한 일련번호의 게시물을 찾아 내용을 반환 하는 메소드(상세보기 메서드)
	public MVCBoardDTO selectView(String idx) { //일련번호 받기
		MVCBoardDTO dto = new MVCBoardDTO();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//쿼리문
		String query = "select * from mvcboard where idx=? ";
		try {
			psmt = getCon().prepareStatement(query);
			psmt.setString(1, idx); //파라미터에 일련번호 입력
			rs = psmt.executeQuery(); //쿼리 실행
			System.out.println("쿼리문 : "+query);
			
			if(rs.next()) { //dto에 값 저장
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
		}catch(Exception e){
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}		
		return dto; //저장된 내용을 전부 반환
	}
	
	//선택한 게시물의 조회수를 1 증가시키는 메소드
	public void updateVisitCount(String num) {
		PreparedStatement psmt = null;

		//쿼리문
		String query = "update mvcboard set visitcount=visitcount+1 where idx = ?";
		//System.out.println("쿼리문 : "+query);
		try {
			psmt = getCon().prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
			//System.out.println("쿼리문 : "+query);
		}catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	//다운로드 횟수를 증가시키는 메서드
	public void downCountPlus(String idx) {
		PreparedStatement psmt = null;
		String query = "update mvcboard set downcount=downcount+1 where idx = ?";
		try {
			psmt = getCon().prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
			//System.out.println("쿼리문 : "+query);
		}catch(Exception e) {
			System.out.println("다운로드수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	//입력한 비밀번호와 저장한 게시물의 비밀번호가 일치하는지 확인하는 메서드
	public boolean confirmPassword(String pass, String idx) {
		PreparedStatement psmt = null;
		boolean isCorr = true;
		try {
			String query = "select count(*) from mvcboard where pass = ? and idx= ? ";
			psmt = getCon().prepareStatement(query);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				isCorr = false;
			}
			
		}catch(Exception e) {
			isCorr = false;
			e.printStackTrace();
		}
		return isCorr;
	}
	
	//해당 일련번호의 게시글을 삭제하는 메서드
	public int deletePost(String idx) {
		PreparedStatement psmt = null;
		int result = 0;
		try {
			String query = "delete from mvcboard where idx= ?";
			psmt = getCon().prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}		
		return result;
	}
	
	//게시글 수정하는 메서드
	public int updatePost(MVCBoardDTO dto) {
		PreparedStatement psmt = null;
		int result = 0;
		try {
			String query = "update mvcboard set title = ? , name = ? , content = ? , ofile = ? , sfile = ? "
							+ " where idx= ? and pass = ?";
			psmt = getCon().prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			
			result = psmt.executeUpdate(); //쿼리문 실행
			
		}catch(Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
}
