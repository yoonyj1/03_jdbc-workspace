package com.netflix.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.netflix.model.vo.Member;

public class MemberDao {
	String sql = null;
	String type = null;
	boolean dml = true;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;

	int result = 0;

	/**
	 * DML문을 처리하는 메소드
	 * @param type
	 * @param m
	 * @return
	 */
	public int dml(String type, Member m) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

				if (type.equals("추가")) {
					sql = "INSERT INTO MEMBER2 VALUES(?, ?, ?, ?, ?)";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, m.getMemId());
					pstmt.setString(2, m.getGrade());
					pstmt.setString(3, m.getNickname());
					pstmt.setDate(4, m.getSignUpDate());
					pstmt.setInt(5, m.getPoint());
				} else if (type.equals("수정")) {
					sql = "UPDATE MEMBER2 SET NICKNAME = ? , POINT = ? WHERE MEMID = ?";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, m.getNickname());
					pstmt.setInt(2, m.getPoint());
					pstmt.setString(3, m.getMemId());
				} else if (type.equals("삭제")) {
					sql = "DELETE FROM MEMBER2 WHERE MEMID = ?";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, m.getMemId());
				}
				result = pstmt.executeUpdate();

				if (result > 0) {
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
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		} 

	public ArrayList<Member> selectMenu() {
		ArrayList<Member> list = new ArrayList<>();

		sql = "SELECT * FROM MEMBER2 ORDER BY DECODE(GRADE, 'Basic', 1, 'Stand', 2, 'Premium', 3)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Member(
						 rset.getString("MEMID"),
						 rset.getString("GRADE"),
						 rset.getString("NICKNAME"),
						 rset.getDate("SIGNUP_DATE"),
						 rset.getInt("POINT")
						 )
						);
				
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	} // selectMenu end

	public ArrayList<Member> selectMenu(int menu, String info) {
		ArrayList<Member> list = new ArrayList<>();


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			if (menu == 1) {
				sql = "SELECT * FROM MEMBER2 WHERE MEMID = ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, info);
			} else if (menu == 2) {
				sql = "SELECT * FROM MEMBER2 WHERE NICKNAME = ?";
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, info);
			}

			rset = pstmt.executeQuery();
			
			Member m = new Member();
			
			while (rset.next()) {
				m = new Member();

				m.setMemId(rset.getString("MEMID"));
				m.setGrade(rset.getString("GRADE"));
				m.setNickname(rset.getString("NICKNAME"));
				m.setSignUpDate(rset.getDate("SIGNUP_DATE"));
				m.setPoint(rset.getInt("POINT"));

				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	} // selectMenu end

} // class end
