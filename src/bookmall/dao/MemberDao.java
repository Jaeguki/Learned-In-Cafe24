package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;

public class MemberDao {
	public boolean insert(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "insert into member values(null, ?, ?, ?, ?)";
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTell());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());
			
			// 4. Execute SQL
			int count = pstmt.executeUpdate();
			result = count == 1;
		}catch(SQLException e) {
			System.out.println("error : " + e);
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public List<MemberVo> getList(){
		List<MemberVo> result = new ArrayList<MemberVo>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "select no, name, tell, email, password from member";

			
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			
			// 4. Execute SQL
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String tell = rs.getString(3);
				String email= rs.getString(4);
				String password = rs.getString(5);
				MemberVo vo = new MemberVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setTell(tell);
				vo.setEmail(email);
				vo.setPassword(password);
				result.add(vo);
			}
			
		}catch(SQLException e) {
			System.out.println("error : " + e);
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. Connect
			String url = "jdbc:mariadb://192.168.1.176:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
