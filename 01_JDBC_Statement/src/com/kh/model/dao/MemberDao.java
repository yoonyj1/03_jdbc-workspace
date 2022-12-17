package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

// DAO(Data Access Object): DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과 받기(JDBC)
// 							결과를 다시 Controller로 다시 리턴

public class MemberDao {

	/*
	 * * JDBC용 객체
	 *  - Connection: DB의 연결정보를 담고있는 객체
	 *  - [Prepared]Statement: 연결 된 DB에 SQL문을 전달해서 실행하고 그 결과를 받아내는 객체 	**중요**
	 *  - ResultSet: SELECT문 실행 후 조회된 결과물들이 담겨있는 객체
	 *  
	 * * JDBC 과정 ( *순서 중요* )
	 *  1) jdbc driver 등록: 해당 DBMS(오라클)가 제공하는 클래스 등록
	 *  2) Connection 생성: 연결하고자 하는 DB정보를 입력해서 해당 DB와 연결하면서 생성
	 *  3) Statement 생성: Connection 객체를 이용해서 생성(SQL문을 실행 및 결과 받는 객체)
	 *  4) SQL문 전달하면서 실행: Statement 객체를 이용해서 SQL문 실행
	 *  5) 결과 받기
	 *  	> SELECT문 실행 => ResultSet 객체 (조회된 데이터들이 담겨있음) => 6-1 
	 *  	> DML문 실행 	  => int (처리된 행 수) => 6-2
	 *  6-1) ResultSet에 담겨있는 데이터들을 하나씩 뽑아서 vo객체에 옮겨담기 [+ 여러행 조회시에는 ArrayList에 담기]
	 *  6-2) 트랜젝션 처리(성공적으로 수행했으면 COMMIT, 실패 시 ROLLBACK)
	 *  7) 다 사용한 JDBC 객체 반드시 반납 => 안할 시 락 걸림 (close) => 생성 된 역순으로 반납  
	 */
	
	/**
	 * 사용자가 입력한 정보들을 추가시켜주는 메소드
	 * @param m: 사용자가 입력한 값들이 담겨있는 Member 객체
	 * @return: INSERT문 수행 후 처리된 행 수
	 */
	public int insertMember(Member m) {
		// insert문 -> 처리된 행 수 return(int) -> 트랜잭션 처리
		
		// 필요한 변수 셋팅
		int result = 0;		// 처리된 결과(행 수)를 받아줄 변수
		Connection conn = null; // 연결된 DB의 연결 정보를 담는 객체
		Statement stmt = null; // "완성된 SQL문(실제 값이 다 채워진 상태로)" 전달해서 곧바로 실행 후 결과 받는 객체
		
		// 실행할 SQL문(완성 된 형태로 만들기)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX, 'XXXX', 'XXXXXX', 'XXXXXXX', 'XXX', SYSDATE)
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, "
										+ "'" + m.getUserId() + "', "
										+ "'" + m.getUserPwd() + "', "
										+ "'" + m.getUserName() + "', "
										+ "'" + m.getGender() + "', "
											  + m.getAge() + ", "
										+ "'" + m.getEmail() + "', "
										+ "'" + m.getPhone() + "', "
										+ "'" + m.getAddress() + "', "
										+ "'" + m.getHobby() + "', SYSDATE)";
		
		// System.out.println(sql); 콘솔에 찍어서 쿼리문 맞는 지 확인
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 생성 == DB에 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
		
			// 3) Statement 생성
			stmt = conn.createStatement();
			
			// 4)5) 실행
			result = stmt.executeUpdate(sql);
			
			// 6) 트랜잭션 처리
			if (result > 0) { // 성공시 커밋
				conn.commit();
			} else { // 실패 시 롤백
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { 
				// 7) 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	} // insertMember end
	
	public ArrayList<Member> selectList() {
		// select문 -> 여러 행 조회 => ResultSet 객체 => ArrayList에 담기
		// 필요한 변수 셋팅
		ArrayList<Member> list = new ArrayList<Member>(); // 현재 상태는 텅 비어있는 상태
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // select문 실행 시 조회된 결과값들이 최초로 담기는 객체
		
		// 실행할 sql문
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 생성
			stmt = conn.createStatement();
			
			// 4)5) 실행 / 결과받기
			rset = stmt.executeQuery(sql);
			
			// 6) ResultSet으로부터 데이터 하나씩 뽑아서 vo객체에 담고 list에 vo 객체 추가
			while(rset.next()) {
				// 현재 rset의 커서가 가리키고 있는 한 행의 데이터를 싹 뽑아서 Member 객체에 담기
				Member m = new Member();
				
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
				list.add(m); // 리스트에 해당 회원 객체 담기
			}
			
			// 반복문이 다 끝난 시점에 
			// 만약에 조회된 데이터가 없었다면 list가 빈 상태
			// 만약 조회된 데이터가 있다면 list에 담긴 상태
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			// 7) 자원 반납
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 사용자가 입력한 이름으로 회원정보를 검색해주는 메소드
	 * @param userName
	 * @return
	 */
	public ArrayList<Member> selectNameInfo(String userName) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME = '" + userName + "'";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				Member m = new Member();
				
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
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
	}
} // class end