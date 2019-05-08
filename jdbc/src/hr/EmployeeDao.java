package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import test.Configure;

public class EmployeeDao {

	public List<EmployeeVo> getList(String keyword) {
		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC Driver(MariaDB) Loading
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. Connect
			String url = "jdbc:mariadb://" + Configure.SERVER_IP + ":" + Configure.SERVER_PORT + "/" + "employees";
			conn = DriverManager.getConnection(url, Configure.DB_ID, Configure.DB_PWD);
			
			// 3. Create STMT
			stmt = conn.createStatement(); 
			
			// 4. Execute SQL
			String sql = "select emp_no, first_name, last_name, hire_date " + 
					"from employees " + 
					"where first_name like ? " + 
					"or last_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				result.add(vo);
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("driver loading failed " + e);
		}catch(SQLException e) {
			System.out.println("error : " + e);
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
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

		
		// TODO Auto-generated method stub
		return result;
	}

}
