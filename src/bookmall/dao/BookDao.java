package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;


public class BookDao {
	public boolean insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "insert into book values(null, ?, ?, ?)";
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getCategoryNo());
			pstmt.setString(2, vo.getTitle());
			pstmt.setLong(3, vo.getPrice());
			
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
	
	
	public List<BookVo> getList(){
		List<BookVo> result = new ArrayList<BookVo>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "select no, category_no, title, price from book";

			
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			
			// 4. Execute SQL
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				Long categoryNo = rs.getLong(2);
				String title = rs.getString(3);
				Long price = rs.getLong(4);
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setCategoryNo(categoryNo);
				vo.setTitle(title);
				vo.setPrice(price);
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
