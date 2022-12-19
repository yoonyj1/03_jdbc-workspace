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
	 * 
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {
		// 받은 값들을 데이터를 직접적으로 처리해주는 DAO로 넘기기
		// => 어딘가에 담아서 전달
		// 어딘가 => Member 객체
		
		// 방법 1) 기본생성자로 생성한 후에 각 필드에 setter메소드 통해서 일일이 담는 방법 => 매개변수가 몇 개 없을 때
		// 방법 2) 매개변수 생성자를 통해서 생성과 동시에 담는 방법
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		// 여기서 나이를 반드시 int형으로 바꿔야함
		// String -> int로 변경하는 방법 => parseInt();
		
		// new부터 빨간줄이 뜨는 경우 => 999999999% 확률로 그 데이터 타입을 받는 매개변수 생성자가 없다는 것임
		int result = new MemberDao().insertMember(m);
		
		// 팝업창 뜨는 거 마냥 사용자에게 문구 보여주기
		if (result > 0) { // 성공
			new MemberMenu().displaySuccess("성공적으로 회원 추가되었습니다.");
		} else { // 실패
			new MemberMenu().displayFail("회원추가 실패했습니다.");
		}
	} // insertMember end
	
	/**
	 * 사용자의 회원 전체 조회요청을 처리해주는 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		
		// 조회결과가 있는지 없는지 판단 후 사용자가 보게 될 응답화면 지정
		if (list.isEmpty()) {
			new MemberMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	} // selectList end
	
	public void selectByUserId(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		
		if (m == null) { // 검색결과가 없을 경우(조회된 데이터 X)
			new MemberMenu().displayNoData(userId + "에 해당하는 결과가 없습니다.");
		} else {
			new MemberMenu().displayMember(m);
		}
	} // selectByUserId end
	
	/**
	 * 
	 * @param keyword: 사용자에게 입력받은 키워드
	 */
	public void selectByUserName(String keyword) {
		new MemberDao().selectByUserName(keyword);
	}
	
	/**
	 * 이름으로 회원 정보를 조회요청을 처리해주는 메소드
	 * @param userName
	 */
	public void selectNameInfo(String userName) {
		ArrayList<Member> list = new MemberDao().selectNameInfo(userName);
		
		if (list.isEmpty()) {
			new MemberMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	} // selectNameInfo end
	
} // class end
