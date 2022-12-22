package com.netflix.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.netflix.common.JDBCTemplate.*;
import com.netflix.model.vo.Member;

public class MemberDao {
	String sql = null;
	String type = null;
	boolean dml = true;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;

	int result = 0;
	
	
	private Properties prop = new Properties();
	
	public MemberDao () {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DML문을 처리하는 메소드
	 * @param type
	 * @param m
	 * @return
	 */
	public int dml(Connection conn, String type, Member m) {
			try {
				if (type.equals("추가")) {
					sql = prop.getProperty("dmlInsert");
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, m.getMemId());
					pstmt.setString(2, m.getGrade());
					pstmt.setString(3, m.getNickname());
					pstmt.setDate(4, m.getSignUpDate());
					pstmt.setInt(5, m.getPoint());
				} else if (type.equals("수정")) {
					sql = prop.getProperty("dmlUpdate");
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, m.getNickname());
					pstmt.setInt(2, m.getPoint());
					pstmt.setString(3, m.getMemId());
				} else if (type.equals("삭제")) {
					sql = prop.getProperty("dmlDelete");
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, m.getMemId());
				}
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		} 

	public ArrayList<Member> selectMenu(Connection conn) {
		ArrayList<Member> list = new ArrayList<>();

		sql = prop.getProperty("selectMenu");

		try {
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return list;
	} // selectMenu end

	public ArrayList<Member> selectMenu(Connection conn, int menu, String info) {
		ArrayList<Member> list = new ArrayList<>();


		try {

			if (menu == 1) {
				sql = prop.getProperty("selectMenu1");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, info);
			} else if (menu == 2) {
				sql = prop.getProperty("selectMenu2");
				
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return list;
	} // selectMenu end

} // class end
