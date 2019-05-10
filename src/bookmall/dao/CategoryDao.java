package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {
	public boolean insert(CategoryVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "insert into category values(null, ?)";
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCategory());
			
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
	
	
	public List<CategoryVo> getList(){
		List<CategoryVo> result = new ArrayList<CategoryVo>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "select no, category from category";

			
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			
			// 4. Execute SQL
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String category = rs.getString(2);
				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setCategory(category);
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
			String url = "jdbc:mariadb://localhost:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
