package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

public class MemberDao {
	
	/**
	 * 회원 추가하는 메소드
	 * @param m: 
	 * @return: 처리된 행 수 
	 */
	public int insertMember(Member m) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			pstmt = conn.prepareStatement(sql); // 생성과 동시에 SQL문 넘겨줌

			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			result = pstmt.executeUpdate(); // 여기서는 SQL문 전달하지 않고 그냥 실행 => 이미 pstmt에 SQL문 들어가 있음

			// 여기서 SQLException 날 수도 있기 때문에 뒤에 커밋, 롤백 있으면 실행이 안됨
			// 문제가 생길 시 무조건 롤백을 해야함

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// 원래는 이렇게 돼야함
		if (result > 0) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 여기서 반납
		try {
			if(pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	} // insertMember end
	
	public ArrayList<Member> selectList() {
		
		ArrayList<Member> list = new ArrayList<Member>(); // 텅빈 리스트
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER ORDER BY USERNO";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql); // 애초에 완성된 SQL문 담았음
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Member(rset.getInt("USERNO"), 
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
	} // selectList end
	
	public Member selectByUserId(String userId) {
		Member m = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USERNO"),
							   rset.getString("USERID"), 
							   rset.getString("USERPWD"),
							   rset.getString("USERNAME"),
							   rset.getString("gender"),
							   rset.getInt("age"), 
							   rset.getString("email"),
							   rset.getString("phone"),
							   rset.getString("ADDRESS"),
							   rset.getString("HOBBY"),
							   rset.getDate("ENROLLDATE"));
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
		return m;
	} // selectByUserID end
	
	public ArrayList<Member> selectByUserName(String keyword) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Member(rset.getInt("USERNO"), 
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
	} // selectByUserName end
	
	public int updateMember(String userId, String userPwd, String email, String phone, String address) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userPwd);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setString(5, userId);
			
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
	} // updateMember end
	
	public int deleteMember(String userId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
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
	} // deleteMember end
	
	public ArrayList<Member> selectNameInfo(String userName) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userName);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Member(rset.getInt("USERNO"), 
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
	}
	
	public int login(String adminId, String adminPwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int result = 0;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ? AND USERPWD = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPwd);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = 1;
			} else {
				result = 2;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	} // login end
} // class end
