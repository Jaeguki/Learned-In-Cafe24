package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static void main(String[] args) {
		boolean result = update(1L, "경영지원팀");
		if(result) {
			System.out.println("성공");
		}
	}
	
	public static boolean update(Long no, String name) {
		boolean result = false;
		
		Connection conn = null;
		Statement stmt = null;
		try {
			// 1. JDBC Driver(MariaDB) Loading
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. Connect
			String url = "jdbc:mariadb://" + Configure.SERVER_IP + ":" + Configure.SERVER_PORT + "/" + "webdb";
			conn = DriverManager.getConnection(url, Configure.DB_ID, Configure.DB_PWD);

			stmt = conn.createStatement();
			
			String sql = "update department"
					+ " set name = '" + name + "'"
							+ " where no=" + no;
			int count = stmt.executeUpdate(sql);
			result = count == 1;
			
			
		}catch(ClassNotFoundException e) {
			System.out.println("driver loading failed " + e);
		}catch(SQLException e) {
			System.out.println("error : " + e);
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
