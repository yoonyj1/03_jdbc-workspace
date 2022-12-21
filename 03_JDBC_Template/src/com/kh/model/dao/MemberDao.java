package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;

public class MemberDao {

	public int insertMember(Connection conn, Member m) {
		// insert => 처리된 행 수 => 트랜잭션 처리
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			// 3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			// 4)5) SQL문 실행 & 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			// conn은 아직 반납하면 안됨 => 트랜잭션 처리 때 필요함
		}
		
		return result;
	} // insertMember end
	
	public int deleteMember(Connection conn, String userId) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	} // deleteMember end
	
	public ArrayList<Member> selectList(Connection conn) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER ORDER BY USERNAME";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Member(
									rset.getInt("USERNO"),
									rset.getString("USERID"),
									rset.getString("USERPWD"),
									rset.getString("USERNAME"),
									rset.getString("GENDER"),
									rset.getInt("AGE"),
									rset.getString("EMAIL"),
									rset.getString("PHONE"),
									rset.getString("ADDRESS"),
									rset.getString("HOBBY"),
									rset.getDate("ENROLLDATE")
									));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public Member selectByUserId (Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member m = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				m = new Member(rset.getInt("USERNO"),
									  rset.getString("USERID"),
									  rset.getString("userPwd"),
									  rset.getString("userName"),
									  rset.getString("gender"),
									  rset.getInt("age"),
									  rset.getString("email"),
									  rset.getString("phone"),
									  rset.getString("address"),
									  rset.getString("hobby"),
									  rset.getDate("enrollDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		
		return m;
	}
	
	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("USERNO"),
									  rset.getString("USERID"),
									  rset.getString("userPwd"),
									  rset.getString("userName"),
									  rset.getString("gender"),
									  rset.getInt("age"),
									  rset.getString("email"),
									  rset.getString("phone"),
									  rset.getString("address"),
									  rset.getString("hobby"),
									  rset.getDate("enrollDate")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int updateMember(Connection conn, String userId, String userPwd, String email, String phone, String address) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userPwd);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setString(5, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Member> selectNameInfo(Connection conn, String userName){
		ArrayList<Member> list = new ArrayList<Member>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userName);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Member(rset.getInt("USERNO"),
						  rset.getString("USERID"),
						  rset.getString("userPwd"),
						  rset.getString("userName"),
						  rset.getString("gender"),
						  rset.getInt("age"),
						  rset.getString("email"),
						  rset.getString("phone"),
						  rset.getString("address"),
						  rset.getString("hobby"),
						  rset.getDate("enrollDate")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int login(Connection conn, String adminId, String adminPwd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int result = 0;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ? AND USERPWD = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPwd);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = 1;
			} else {
				result = 2;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
} // class end
