package com.netflix.service;

import static com.netflix.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.netflix.model.dao.MemberDao;
import com.netflix.model.vo.Member;

public class MemberService {

	public int dml (String type, Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().dml(conn, type, m);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectMenu() {
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().selectMenu(conn);
		
		return list;
	}
	
	public ArrayList<Member> selectMenu(int menu, String info) {
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDao().selectMenu(conn, menu, info);
		
		return list;
	}
}
