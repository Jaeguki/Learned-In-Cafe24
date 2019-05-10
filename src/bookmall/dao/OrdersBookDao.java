package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrdersBookVo;

public class OrdersBookDao {

	public boolean insert(OrdersBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "insert into orders_book values(null, ?, ?, ?)";
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCount());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getOrdersNo());
			
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
	
	
	public List<OrdersBookVo> getList(){
		List<OrdersBookVo> result = new ArrayList<OrdersBookVo>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "select * from orders_book";
			
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			
			// 4. Execute SQL
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				Integer count = rs.getInt(2);
				Long bookNo = rs.getLong(3);
				Long ordersNo= rs.getLong(4);
				OrdersBookVo vo = new OrdersBookVo();
				vo.setNo(no);
				vo.setCount(count);
				vo.setBookNo(bookNo);
				vo.setOrdersNo(ordersNo);
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
