package com.kh.view;

import java.util.Scanner;

import com.kh.controller.MemberController;

// View: 사용자가 보게 될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {
	// Scanner 객체 생성(전역으로 다 쓸 수 있도록)
	private Scanner sc = new Scanner(System.in);
	
	// MemberController 객체 생성(전역에서 바로 요청할 수 있게끔)
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게 될 첫 화면(메인 화면)
	 */
	public void mainMenu() {
		
	}
}
