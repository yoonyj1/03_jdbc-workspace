package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

/*
 *  Controller: View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
 *  			해당 메소드로 전달된 데이터 [가공처리 한 후] DAO로 전달
 *  			DAO로부터 반환받은 결과에 따라 성공인지 실패인지 판단 후 응답 화면 결정
 */

public class MemberController {

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby: 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		
		int result = new MemberDao().insertMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 추가 완료");
		} else {
			new MemberMenu().displayFail("회원 추가 실패");
		}
	} // insertMember end
	
	
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		
		if (list.isEmpty()) {
			new MemberMenu().displayNoData("조회 결과 없음");
		} else {
			new MemberMenu().displayMemberList(list);
		}
		
	} // selectList end
	
	public void selectByUserId(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		
		if (m == null) {
			new MemberMenu().displayFail("조회 결과 없음");
		} else {
			new MemberMenu().displayMember(m);
		}
	} // selectByUserId end
	
	/**
	 * 사용자의 이름으로 키워드 검색 요청 시 처리해주는 메소드
	 * @param keyword: 사용자에게 입력받은 키워드
	 */
	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if (list.isEmpty()) {
			new MemberMenu().displayFail("조회 결과 없음");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	
	}
	
	/**
	 * 정보변경 요청 처리해주는 메소드
	 * @param userId: 변경하고자 하는 회원 아이디
	 * @param userPwd: 변경할 비밀번호
	 * @param email: 변경할 이메일
	 * @param phone: 변경할 전화번호
	 * @param address: 변경할 주소
	 */
	public void updateMember(String userId, String userPwd, String email, String phone, String address) {
		int result = new MemberDao().updateMember(userId, userPwd, email, phone, address);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("변경 완료");
		} else {
			new MemberMenu().displayFail("변경 실패");
		}
	}
	
	/**
	 * 사용자에게 삭제할 아이디를 입력받아 회원정보를 삭제 처리 요청하는 메소드
	 * @param userId: 삭제할 아이디
	 */
	public void deleteMember(String userId) {

		int result = new MemberDao().deleteMember(userId);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("삭제 완료");
		} else {
			new MemberMenu().displayFail("삭제 실패");
		}
	}
	
	/**
	 * 이름으로 회원 정보를 조회요청을 처리해주는 메소드
	 * @param userName
	 */
	public void selectNameInfo(String userName) {
		ArrayList<Member> list = new MemberDao().selectNameInfo(userName);
		
		if (list.isEmpty()) {
			new MemberMenu().displayFail("조회 결과 없음");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	
	} // selectNameInfo end
	
	public int login(String adminId, String adminPwd) {
		int result = new MemberDao().login(adminId, adminPwd);

		return result;
	}
} // class end
