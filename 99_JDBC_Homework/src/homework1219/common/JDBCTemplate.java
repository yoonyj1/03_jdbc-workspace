package homework1219.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JDBCTemplate {

	
	/**
	 * 1. Connection 객체 생성 (DB와 접속)한 후 해당 Connection 객체 반환해주는 메소드
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	} // getConnection end
	
	/**
	 * 2. COMMIT을 처리해주는 메소드 (Connection 객체 전달받아서)
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // commit end
	
	/**
	 * 3. ROLLBACK을 처리해주는 메소드(Connection 객체 전달받아서)
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // rollback end
	
	// JDBC용 객체 전달 받아서 반납처리 해주는 메소드
	/**
	 * 4. Statement 관련 객체 전달받아서 반납시켜주는 메소드
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		// * PreparedStatement 전용 close 안만들어도 됨 => Statement가 부모임 ==> 다형성 사용 *
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // close end
	
	/**
	 * 5. Connection 관련 객체 전달받아서 반납시켜주는 메소드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // close end
	
	/**
	 * 6. ResultSet 관련 객체 전달받아서 반납시켜주는 메소드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
