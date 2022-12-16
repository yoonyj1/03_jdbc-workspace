package com.kh.controller;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

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
		new MemberDao().insertMember(m);
	}
}
