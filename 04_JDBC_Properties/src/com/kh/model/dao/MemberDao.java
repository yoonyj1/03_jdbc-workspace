package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;

public class MemberDao {
	/*
	 * 기존의 방식: DAO 클래스에 사용자가 요청할 때마다 실행해야하는 SQL문을 자바 소스코드 내에 명시적으로 작성 => 정적코딩방식
	 * 
	 * > 문제점: SQL문을 수정해야할 경우 자바소스코드 파일을 수정해야함 => 수정된 내용을 반영시키고자 한다면 프로그램 재구동해야함
	 * 
	 * > 해결방식: SQL문들을 별도로 관리하는 외부파일(.xml)을 만들어서 실시간으로 그 파일에 기록된 SQL문을 읽어들여서 실행 => 동적코딩방식
	 * 			여러줄을 쓸 수 있도록 xml로 하는 게 좋음 
	 */

	private Properties prop = new Properties();
	
	// 사용자가 어떤 서비스를 요청할 때마다 결국 new MemberDao().xxx; 호출
	// 즉, 서비스를 요청할 때마다 기본생성자가 매번 실행됨
	
	public MemberDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public int insertMember(Connection conn, Member m) {
		// insert => 처리된 행 수 => 트랜잭션 처리
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
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
		
		String sql = prop.getProperty("deleteMember");
		
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
		
		String sql = prop.getProperty("selectList");
		
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
		
		String sql = prop.getProperty("selectByUserId");
		
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
		
		String sql = prop.getProperty("selectByUserName");
		
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
		
		String sql = prop.getProperty("updateMember");
		
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
		
		String sql = prop.getProperty("selectNameInfo");
		
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
		
		String sql = prop.getProperty("login");
		
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
