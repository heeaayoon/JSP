package model1.board;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect {
	//세번째 생성자 이용
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	//board 테이블에 저장된 게시물의 갯수를 반환하는 메서드
	public int seletCount(Map<String, Object> map) {
		int totalCount = 0;
		
		String query = "select count(*) from board";
		if(map.get("searchWord")!=null) { //Map 컬렉션에 "searchWord" 키로 저장된 값이 있는 경우에만 -> where절 추가
			query += " where " + map.get("searchField")+" "+" Like '%"+map.get("searchWord")+ "%'";
		}
		//System.out.println("쿼리문 : "+query);
		try {
			Statement stmt = getCon().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1); //ResultSet 객체의 1번 인덱스의 결과를 정수로 추출 -> 왜?
		}catch(Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}	
		return totalCount;
	}
	
	//게시물 목록을 가져오는 메서드
	public List<BoardDTO> selectList(Map<String, Object> map){
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		
		String query = "select * from board";
		if(map.get("searchWord")!=null) { //Map 컬렉션에 "searchWord" 키로 저장된 값이 있는 경우에만 -> where절 추가
			query += " where " + map.get("searchField")+" "+" Like '%"+map.get("searchWord")+ "%'";
		}
		query += " order by num desc"; //최근게시물이 상단에 출력되도록 내림차순 정렬
		//System.out.println("쿼리문 : "+query);
		try {
			Statement stmt = getCon().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}	
		return bbs;
	}
	
	//페이징 기능을 지원하는 메서드
	public List<BoardDTO> selectListPage(Map<String, Object> map){
		List<BoardDTO> bbs = new Vector<BoardDTO>(); //결과(게시물 목록)을 담을 변수
		
		//쿼리문
		String query = "select * from board ";
		//쿼리문 검색 조건 추가 
		//MySql 쿼리문 LIMIT [건너뛸 개수], [가져올 개수]
		if(map.get("searchWord") != null){
			 query+= " WHERE " + map.get("searchField")
			 + " LIKE '%" + map.get("searchWord") + "%' ";
			 }
		query+= " ORDER BY num DESC limit ?,? ";
		
		try{
			PreparedStatement psmt = getCon().prepareStatement(query);
			psmt.setInt(1, (int)map.get("start"));
			psmt.setInt(2, (int)map.get("pageSize"));
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
			 
		 }catch(Exception e) {
			 System.out.println("게시물 조회 중 예외 발생 2");
			 e.printStackTrace();
		 }
		
		return bbs;
	}
	
	//글쓰기 메서드
	public int insertWrite(BoardDTO dto) {
		int result = 0; //insert에 성공한 행의 갯수를 반환
		
		try {
			//insert 쿼리문 작성
			String query = "insert into board (title,content,id,visitcount) "
							+ " values (?,?,?,0)";
			
			PreparedStatement psmt = getCon().prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			//System.out.println("쿼리문 : "+query);
			
			result = psmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result; //성공한 갯수 반환
	}
	
	//선택한 게시물을 찾아 내용을 반환 하는 메소드(상세보기 메서드)
	public BoardDTO selectView(String num) { //일련번호 받기
		BoardDTO dto = new BoardDTO();
		
		//쿼리문
		String query = "select B.*, M.name from member M inner join board B "
						+" on M.id = B.id " + " where num=? ";
		
		try {
			PreparedStatement psmt = getCon().prepareStatement(query);
			psmt.setString(1, num); //파라미터에 일련번호 입력
			ResultSet rs = psmt.executeQuery(); //쿼리 실행
			//System.out.println("쿼리문 : "+query);
			
			if(rs.next()) { //dto에 값 저장
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				dto.setName(rs.getString("name"));
			}
		}catch(Exception e){
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}		
		return dto; //저장된 내용을 전부 반환
	}
	
	//선택한 게시물의 조회수를 1 증가시키는 메소드
	public void updateVisitCount(String num) {
		//쿼리문
		String query = "update board set visitcount=visitcount+1 where num = ?";
		//System.out.println("쿼리문 : "+query);
		try {
			PreparedStatement psmt = getCon().prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
			//System.out.println("쿼리문 : "+query);
		}catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	//게시물 수정하는 메소드
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		try {
			//insert 쿼리문 작성
			String query = "update board set title = ?, content = ? where num = ?";
			System.out.println("쿼리문 : "+query);
			PreparedStatement psmt = getCon().prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());			
			result = psmt.executeUpdate();
			System.out.println("쿼리문 : "+query);
			
		}catch(Exception e){
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//게시물 삭제하는 메서드
	public int deletePost(BoardDTO dto) {
		int result = 0;
		try {
			//delete 쿼리문
			String query = "delete from board where num =?";
			
			PreparedStatement psmt = getCon().prepareStatement(query);
			psmt.setString(1, dto.getNum());
			
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
}
