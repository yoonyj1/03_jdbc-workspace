package com.netflix.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.netflix.model.vo.Member;

public class MemberDao {

	String sql = null;
	
	public int insertMenu(Member m) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		sql = "INSERT INTO MEMBER2 VALUES("
				+ "'" + m.getMemId() + "', "
				+ "'" + m.getGrade() + "', "
				+ "'" + m.getNickname() + "', "
				+ m.getSignUpDate() + ", "
				+ m.getPoint() + ")";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	} // insertMenu end
	
	public ArrayList<Member> selectMenu() {
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		sql = "SELECT * FROM MEMBER2";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				Member m = new Member();
				
				m.setMemId(rset.getString("MEMID"));
				m.setGrade(rset.getString("GRADE"));
				m.setNickname(rset.getString("NICKNAME"));
				m.setSignUpDate(rset.getDate("DATE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
} // class end
