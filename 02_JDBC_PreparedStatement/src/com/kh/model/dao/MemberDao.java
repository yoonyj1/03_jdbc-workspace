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

	public void insertMember(Member m) {
		// insert문 => 처리 된 행 수(int) 반환 => 트랜잭션 처리
		// 필요한 변수 셋팅
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문 (미완성된 형태로 둘 수 있음)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX, 'XXXX', 'XXXXXX', 'XXXXXXX', 'XXX', SYSDATE)
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			// 1) jdbc driver 등록 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3)
			pstmt = conn.prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
} // class end
