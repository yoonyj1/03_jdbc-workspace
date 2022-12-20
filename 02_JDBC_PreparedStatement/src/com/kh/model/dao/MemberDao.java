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
	
	/*
	 * * Statement와 PreparedStatement의 특징
	 * - 둘 다 SQL문을 실행하고 결과를 받아내는 객체(둘 중 하나 쓰면됨)
	 * 
	 * - Statement와 PreparedStatement의 차이점
	 * - Statement 같은 경우는 SQL문을 바로 전달하면서 실행시키는 객체
	 * 		(즉, SQL문을 완성 형태로 만들어 둬야함, 사용자가 입력한 값이 다 채워진 형태로)
	 * 
	 * 			> 기존의 Statement 방식
	 * 				1) Connection 객체를 통해 Statement 객체 생성: stmt = conn.createStatement();
	 * 				2) Statement 객체를 통해 "완성된 SQL문: 실행 및 결과 받기: stmt.executeXXX(완성된 SQL);
	 * 
	 * - PreparedStatement 같은 경우 "미완성 된 SQL문"을 잠시 보관해둘 수 있는 객체
	 * 		(즉, 사용자가 입력한 값들을 채워두지 않고 각각 들어갈 공간을 확보만 미리 해놓아도 됨)
	 *  단, 해당 SQL문은 본격적으로 실행하기 전에는 빈 공간을 사용자가 입력한 값으로 채워서 실행하긴 해야함
	 *  
	 *  		> PreparedStatement 방식
	 *  			1) Connection 객체를 통해 PreparedStatement 객체 생성: pstmt = conn.preparedStatement([미완성된] SQL);
	 *  			2) 미완성된 SQL문을 완성시켜야함.					   : pstmt.setXXX(1, "대체할 값");
	 *  			3) 해당 완성된 SQL문을 실행결과 받기					   : 결과 = pstmt.executeXXX();
	 */

	/**
	 * 회원 추가하는 메소드
	 * @param m: 
	 * @return: 처리된 행 수 
	 */
	public int insertMember(Member m) {
		// insert문 => 처리 된 행 수(int) 반환 => 트랜잭션 처리
		// 필요한 변수 셋팅
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		// 실행할 SQL문 (미완성된 형태로 둘 수 있음)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX,
		// 'XXXX', 'XXXXXX', 'XXXXXXX', 'XXX', SYSDATE)
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql); // 생성과 동시에 SQL문 넘겨줌

			// 빈공간을 실제 값(사용자에게 입력받은 값)으로 채워준 후 실행
			// pstmt.setString(홀더순번, 대체할 값) => '대체할 값' ('' 감싸준 상태로 알아서 들어감)
			// pstmt.setInt(홀더순번, 대체할 값) => 대체할 값
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			// 4)5) SQL문 실행 및 결과 받기
			result = pstmt.executeUpdate(); // 여기서는 SQL문 전달하지 않고 그냥 실행 => 이미 pstmt에 SQL문 들어가 있음

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
	} // insertMember end
	
	public ArrayList<Member> selectList() {
		// SELECT문 (여러행) => ResultSet 객체 => ArrayList
		
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
				// 현재 rset이 참조하고 있는 해당 행의 모든 컬럼값 뽑아서 => 한 Member 객체에 담기
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
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";  ==>'%''keyword''%' ==> '%keyword%' 
		
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
	}
} // class end
