package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.CityDTO;
import common.JDBConnect;

public class CityDAO extends JDBConnect  {
	public CityDAO() {
		super();
		System.out.println("CityDAO 객체 생성 및 DB 연결 완료");
	}
	
	// 1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하는 메서드
	public List<CityDTO> method1(Integer population){
		List<CityDTO> dto = new ArrayList<>();
		String query = "select name, population from city";
		
		if(population!=null) {
			query += " WHERE population > ?";
		}
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			psmt = getCon().prepareStatement(query);
			if (population != null) {
                psmt.setInt(1, population);
            }
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CityDTO city = new CityDTO();
				city.setName(rs.getString("name"));
				city.setPopulation(rs.getInt("population"));
//				city.setID(rs.getString("ID")); //불필요한 정보임
//				city.setCountryCode(rs.getString("CountryCode"));
//				city.setDistrict(rs.getString("district"));
				dto.add(city);
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
