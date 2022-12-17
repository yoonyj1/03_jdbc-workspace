package com.netflix.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.netflix.model.dao.MemberDao;
import com.netflix.model.vo.Member;
import com.netflix.view.MemberMenu;

public class MemberController {

	public void insertMenu(int menu, String memId, String nickname, String signUpDate, String point) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		Date signUpDate2 = null;
		
		try {
			signUpDate2 = (Date) sdf.parse(signUpDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Member m = new Member(memId, nickname, signUpDate2, Integer.parseInt(point));
		int result = 0;
		
		if (menu == 1) {
			m.setGrade("Basic");
			result = new MemberDao().insertMenu(m);
		} else if (menu == 2) {
			m.setGrade("Standard");
			result = new MemberDao().insertMenu(m);
		} else if (menu == 3) {
			m.setGrade("Premium");
			result = new MemberDao().insertMenu(m);
		}
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원추가 성공");
		} else {
			new MemberMenu().displayFail("회원추가 실패");
		}
	
	} // insertMenu end
	
	public void selectMenu() {
		ArrayList<Member> list = new MemberDao().selectMenu();
		
		
	}
} // class end
