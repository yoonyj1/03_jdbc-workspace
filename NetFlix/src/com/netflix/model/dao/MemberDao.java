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
	String type = null;
	boolean dml = true;

	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;

	Member m = new Member();

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

				stmt = conn.createStatement();
				if (type.equals("추가")) {
					sql = "INSERT INTO MEMBER2 VALUES(" + 
											"'" + m.getMemId() + "', " 
										  + "'" + m.getGrade() + "', " 
										  + "'"	+ m.getNickname() + "', " 
										  + "'" + m.getSignUpDate() + "', " 
										  + m.getPoint() + ")";
				} else if (type.equals("수정")) {
					sql = "UPDATE MEMBER2 SET NICKNAME = '" + m.getNickname() 
										   + "', POINT = " + m.getPoint()
										   + "WHERE MEMID = '" + m.getMemId() + "'";
				} else if (type.equals("삭제")) {
					sql = "DELETE FROM MEMBER2 WHERE MEMID = '" + m.getMemId() + "'";
				}
				result = stmt.executeUpdate(sql);

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
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		} 

	public ArrayList<Member> selectMenu() {
		ArrayList<Member> list = new ArrayList<>();

		sql = "SELECT * " + "FROM MEMBER2 " + "ORDER BY DECODE(GRADE, 'Basic', 1, 'Stand', 2, 'Premium', 3)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

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
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;
	} // selectMenu end

	public ArrayList<Member> selectMenu(int menu, String info) {
		ArrayList<Member> list = new ArrayList<>();

		if (menu == 1) {
			sql = "SELECT * FROM MEMBER2 WHERE MEMID = '" + info + "'";
		} else if (menu == 2) {
			sql = "SELECT * FROM MEMBER2 WHERE NICKNAME = '" + info + "'";
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

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
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	} // selectMenu end

} // class end
