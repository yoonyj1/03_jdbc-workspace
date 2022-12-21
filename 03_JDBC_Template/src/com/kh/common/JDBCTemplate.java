package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 공통 템플릿(매번 반복적으로 작성될 코드를 메소드로 정의해둘것임)

public class JDBCTemplate {

	// 모든 메소드를 싹 다 static 메소드로 만듦
	// 실행되자마자 메모리 영역에 다 올라감
	// 싱글톤 패턴: 메모리 영역에 단 한번만 올려두고 매번 재사용하는 개념(Math 클래스)
	
	/**
	 * 1. Connection 객체 생성 (DB와 접속)한 후 해당 Connection 객체 반환해주는 메소드
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
}
