package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import test.Configure;

public class MyDriverTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 1. JDBC Driver(MariaDB) Loading
			Class.forName("driver.MyDriver");
			
			// 2. Connect
			String url = "jdbc:mydb://" + Configure.SERVER_IP + ":" + Configure.SERVER_PORT + "/" + "webdb";
			conn = DriverManager.getConnection(url, Configure.DB_ID, Configure.DB_PWD);
			
			System.out.println("Connection Success" + conn);
			
		}catch(ClassNotFoundException e) {
			System.out.println("driver loading failed " + e);
		}catch(SQLException e) {
			System.out.println("error : " + e);
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
