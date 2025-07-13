package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.CountryDTO;
import common.JDBConnect;

public class CountryDAO extends JDBConnect {
	public CountryDAO(String drv, String url, String id, String pw) {
		super();
	}
	
	public static void main(String[] args) {
		CountryDAO dao = new CountryDAO("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/musthave", 
				"musthave", "tiger");
//		CountryDTO dto = dao.method1();
//		System.out.println(dto);
	}

	private CountryDTO method1() {
		CountryDTO dto = new CountryDTO();
		String query = "select * from country";
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = getCon().prepareStatement(query);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setCode(rs.getString("code"));
				dto.setContinent(rs.getString(2));
				dto.setName(rs.getString(3));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return dto;
	}
}
