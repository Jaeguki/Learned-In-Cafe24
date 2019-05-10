package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrdersVo;

public class OrdersDao {
	public boolean insert(OrdersVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "insert into orders values(null, ?, ?)";
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getAddress());
			pstmt.setLong(2, vo.getMemberNo());
			
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
	
	
	public List<OrdersVo> getList(){
		List<OrdersVo> result = new ArrayList<OrdersVo>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver(MariaDB) Loading
			conn = getConnection();
			
			String sql = "select a.no, a.member_no, b.name, b.email, sum(d.price * c.count), a.address from orders a, member b, orders_book c, book d where a.member_no=b.no and a.no=c.orders_no and c.book_no=d.no";
			
			// 3. Create PSTMT
			pstmt = conn.prepareStatement(sql);
			
			// 4. Execute SQL
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				Long memberNo = rs.getLong(2);
				String orderName = rs.getString(3);
				String orderEmail = rs.getString(4);
				Long price= rs.getLong(5);
				String address = rs.getString(6);
				OrdersVo vo = new OrdersVo();
				vo.setNo(no);
				vo.setMemberNo(memberNo);
				vo.setOrderName(orderName);
				vo.setOrderEmail(orderEmail);
				vo.setPrice(price);
				vo.setAddress(address);
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
