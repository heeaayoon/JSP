package membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBConnect;

public class MemberDAO extends JDBConnect{
	
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO(
				"com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/musthave", 
				"musthave", "tiger");
		MemberDTO dto = dao.getMemberDTO("musthave","1234");
		System.out.println(dto);
	}
	
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "select * from member where id =? and pass =?";
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = getCon().prepareStatement(query);
			psmt.setString(1,uid);
			psmt.setString(2,upass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
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
