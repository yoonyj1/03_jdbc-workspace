package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {

	public int insertMember(Member m) {
		// 1) JDBC 드라이버 등록
		// 2) Connection 객체 생성
		Connection conn = /*JDBCTemplate.*/getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		// 6) 트랜잭션 처리
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// Connection 반납
		close(conn);
		
		return result;
	} // insertMember end
	
	public int deleteMember(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	} // deleteMember end
	
	public ArrayList<Member> selectList() {
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().selectList(conn);
		
		close(conn);
		
		return list;
	}
	
	public Member selectByUserId(String userId) {
		Connection conn = getConnection();
		
		Member m = new MemberDao().selectByUserId(conn, userId);
		
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String keyword){
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		close(conn);
		
		return list;
	}
	
	public int updateMember(String userId, String userPwd, String email, String phone, String address) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(conn, userId, userPwd, email, phone, address);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectNameInfo(String userName) {
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().selectNameInfo(conn, userName);
		
		close(conn);
		
		return list;
	}
	
	public int login(String adminId, String adminPwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().login(conn, adminId, adminPwd);
		
		close(conn);
		
		return result;
	}
}
