package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
//DAO
public class JDBConnect2 {
	private Connection con;
	
	//Connection 객체를 외부로 반환할 수 있게 하는 메서드 
	public Connection getCon() {
		return con;
	}

	//기본 생성자
	public JDBConnect2() {
		try {
			//JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//DB에 연결
			String url = "jdbc:mysql://localhost:3306/world";
			String id = "musthave";
			String pwd = "tiger";
			con = DriverManager.getConnection(url,id,pwd);
			
			System.out.println("DB연결 성공(기본 생성자)");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(con != null) con.close();
			System.out.println("JDBC 자원 해제");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//메서드 만들어서 dbmission1~10 전부 가져오기
	//1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요. (City)
	public List<Mission1DTO> getCityByPopulation(Integer num){ //int는 null을 가질 수 x, Integer은 null도 가질 수 있음 
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List <Mission1DTO> list = new ArrayList<Mission1DTO>();
		
		//기본 sql 쿼리
		String sql = "select countrycode, population from city";
		
		try {
			//파라미터가 null이 아닐 경우에만 where절 추가
			if (num != null) {
				sql += " where population > ?";
			}	
			psmt = con.prepareStatement(sql);
			
			if (num != null) {
				psmt.setInt(1, num);
			}	
			
			// 질의 실행
			rs = psmt.executeQuery();
			
			// 커서 프로세싱을 이용한 출력
			while(rs.next()) {
				Mission1DTO dto = new Mission1DTO();
				dto.countrycode = rs.getString("CountryCode");
				dto.population = rs.getInt("Population");
				
				list.add(dto);
			}
			
		}catch (Exception e) {
	        e.printStackTrace();
	    }finally {
		    // 리소스 해제
	    	try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }

		return list;	
	}
}
