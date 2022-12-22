package com.kh.common;

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
		
		/*
		 * 기존의 방식: jdbc driver 구문, 접속할 DB의 url, 접속할 계정명/비번들을 자바소스코드 내에 명시적으로 작성 => 정적코딩방식
		 *  > 문제점: DBMS가 변경되었을 경우, 접속할 DB의 url 또는 계정명, 비번이 변경될 경우 => 자바소스코드를 수정해야함
		 *  		=> 수정된 내용을 반영시키고자한다면 프로그램 재구동해야함. (프로그램이 비정상적으로 종료됐다가 다시 구동)
		 *  		=> 유지보수가 불편
		 *  > 해결방식: DB관련 정보들을 별도로 관리하는 외부파일(.properties)로 만들어서 관리
		 *  		 외부파일로부터 읽어들여서 반영시키면 됨 => 동적코딩방식
		 */
		Connection conn = null;
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), 
											   prop.getProperty("username"), 
											   prop.getProperty("password"));
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
	} // close end
}
