package com.netflix.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.netflix.model.dao.MemberDao;
import com.netflix.model.vo.Member;
import com.netflix.view.MemberMenu;

public class MemberController {
	
	String type = null;
	int result = 0;
	Member m = new Member();
	ArrayList<Member> list = null;

	public void insertMenu(int menu, String memId, String nickname, String signUpDate, String point) {
		 // 951022
		 // 012345
		String sudYear = "20" + signUpDate.substring(0, 2); // 2022
		String sudMonth = signUpDate.substring(2, 4); // 12
		String sudDay = signUpDate.substring(4); // 18
		
		java.util.Date date = new java.util.Date();
		
		signUpDate = sudYear + "-" + sudMonth + "-" + sudDay;  //2022-12-18
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		signUpDate = sdf.format(date);
		
		Date date1 = Date.valueOf(signUpDate);
		
		m = new Member(memId, nickname, date1, Integer.parseInt(point));
		
		result = 0;
		type = "추가";
		
		if (menu == 1) {
			m.setGrade("Basic");
			result = new MemberDao().insertMenu(m);
		} else if (menu == 2) {
			m.setGrade("Stand");
			result = new MemberDao().insertMenu(m);
		} else if (menu == 3) {
			m.setGrade("Premium");
			result = new MemberDao().insertMenu(m);
		}
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 " + type + " 성공");
		} else {
			new MemberMenu().displayFail("회원 " + type + " 실패");
		}
	
	} // insertMenu end
	
	public void selectMenu() {
		list = new MemberDao().selectMenu();
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("조회결과 없음");
		} else {
			new MemberMenu().displayMember(list);
		}
		
	} // selectMenu end
	
	public void selectInfo(int menu, String info) {
		list = new MemberDao().selectMenu(menu, info);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("조회결과 없음");
		} else {
			new MemberMenu().displayMember(list);
		}
	} // selectInfo end
	
	public void updateMenu(String memId, String nickname, String point) {
		type = "수정";
		
		m = new Member(memId, nickname, Integer.parseInt(point));
		
		result = new MemberDao().updateMenu(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 정보 " + type + " 성공");
		} else {
			new MemberMenu().displayFail("회원 정보 " + type + " 실패");
		}
	} // updateMenu end
	
	public void deleteMenu(String memId) {
		type = "삭제";
		
		m = new Member(memId);
		
		result = new MemberDao().deleteMenu(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 정보 " + type + " 성공");
		} else {
			new MemberMenu().displayFail("회원 정보 " + type + " 실패");
		}
	} // deleteMenu end
	
} // class end
