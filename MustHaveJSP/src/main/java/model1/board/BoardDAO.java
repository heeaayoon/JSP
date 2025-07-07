package model1.board;

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
			query += "where" + map.get("searchField")+" "+" Like '%"+map.get("searchWord")+ "%'";
		}
		
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
			query += "where" + map.get("searchField")+" "+" Like '%"+map.get("searchWord")+ "%'";
		}
		query += " order by num desc"; //최근게시물이 상단에 출력되도록 내림차순 정렬
		
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
}
