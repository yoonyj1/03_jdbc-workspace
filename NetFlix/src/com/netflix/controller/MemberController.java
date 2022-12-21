package com.netflix.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.netflix.model.dao.MemberDao;
import com.netflix.model.vo.Member;
import com.netflix.service.MemberService;
import com.netflix.view.MemberMenu;

public class MemberController {
	
	String type = null;
	int result = 0;
	boolean dml = true;
	
	/**
	 * 1. Insert를 처리하는 메소드
	 * @param menu
	 * @param memId
	 * @param nickname
	 * @param signUpDate
	 * @param point
	 */
	public void insertMenu(int menu, String memId, String nickname, String signUpDate, String point) {
		type = "추가";
		dml = true;
		
		String sudYear = "20" + signUpDate.substring(0, 2); // 2022
		String sudMonth = signUpDate.substring(2, 4); // 12
		String sudDay = signUpDate.substring(4); // 18
		
		signUpDate = sudYear + "-" + sudMonth + "-" + sudDay;  //2022-12-18
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date date = null;
		try {
			date = sdf.parse(signUpDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		signUpDate = sdf.format(date);
		
		Date date1 = Date.valueOf(signUpDate);

		Member m = new Member(memId, nickname, date1, Integer.parseInt(point));
		
		if (menu == 1) {
			m.setGrade("Basic");
			result = new MemberService().dml(type, m);
		} else if (menu == 2) {
			m.setGrade("Stand");
			result = new MemberService().dml(type, m);
		} else if (menu == 3) {
			m.setGrade("Premium");
			result = new MemberService().dml(type, m);
		}
		
		dml(type);
	
	} // insertMenu end
	
	/**
	 * 2. Select를 실행하는 메소드
	 */
	public void selectMenu() {
		dml = false;
		
		ArrayList<Member> list = new MemberService().selectMenu();
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("조회결과 없음");
		} else {
			new MemberMenu().displayMember(list);
		}
		
	} // selectMenu end
	
	/**
	 * 3. Select를 ID 혹은 nickname으로 검색하는 메소드
	 * @param menu
	 * @param info
	 */
	public void selectInfo(int menu, String info) {
		dml = false;
		
		ArrayList<Member> list = new MemberService().selectMenu(menu, info);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("조회결과 없음");
		} else {
			new MemberMenu().displayMember(list);
		}
	} // selectInfo end
	
	/**
	 * 4. 업데이트 메소드
	 * @param memId
	 * @param nickname
	 * @param point
	 */
	public void updateMenu(String memId, String nickname, String point) {
		dml = true;
		type = "수정";
		
		Member m = new Member(memId, nickname, Integer.parseInt(point));
		
		result = new MemberService().dml(type, m);
		
		dml(type);
	} // updateMenu end
	
	/**
	 * 5. 삭제메소드
	 * @param memId
	 */
	public void deleteMenu(String memId) {
		dml = true;
		type = "삭제";
		
		Member m = new Member(memId);
		
		result = new MemberService().dml(type, m);
		
		dml(type);
	} // deleteMenu end
	
	/**
	 * DML문의 처리결과를 나타내는 메소드
	 * @param type
	 */
	public void dml(String type) {
		if (type.equals("추가")) {
			if (result > 0) {
				new MemberMenu().displaySuccess("회원 " + type + " 성공");
			} else {
				new MemberMenu().displayFail("회원 " + type + " 실패");
			}
		} else {
			if (result > 0) {
				new MemberMenu().displaySuccess("회원 정보 " + type + " 성공");
			} else {
				new MemberMenu().displayFail("회원 정보 " + type + " 실패");
			}
		}
	}
	
} // class end
