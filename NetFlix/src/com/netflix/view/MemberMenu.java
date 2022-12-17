package com.netflix.view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.netflix.controller.MemberController;

public class MemberMenu {

	Scanner sc = new Scanner(System.in);
	MemberController mc = new MemberController();

	public void mainMenu() {

		while (true) {
			System.out.println("\n *** Netflix 회원 관리 시스템 ***");
			System.out.println("1. 신규 회원 등록"); // INSERT
			System.out.println("2. 회원 목록 출력"); // SELECT
			System.out.println("3. 회원 정보 검색"); // SELECT *WHERE
			System.out.println("4. 회원 정보 수정"); // UPDATE
			System.out.println("5. 회원 정보 삭제"); // DELETE
			System.out.println("6. 시스템 종료");

			System.out.print(">> ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				insertMenu();
				break;

			case 2:
				mc.selectMenu();
				break;

			case 3:
				break;

			case 4:
				break;

			case 5:
				break;

			case 6:
				System.out.println("시스템을 종료합니다");
				sc.close();
				return;

			}
		} // while end
		
	} // mainMenu end
	
	/**
	 * 1. 신규 회원 등록 화면
	 */
	public void insertMenu() {
		
		while (true) {
			System.out.println("회원 등급 선택");
			System.out.println("1. Basic");
			System.out.println("2. Standard");
			System.out.println("3. Premium");
			System.out.print(">> ");
			
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1: case 2: case 3:
				System.out.print("신규 회원 ID(7자 이내): ");
				String memId = sc.nextLine();
				
				System.out.print("신규 회원 닉네임(4자 이내): ");
				String nickname = sc.nextLine();
				
				System.out.print("신규 회원 가입일(951022 형식으로): ");
				String signUpDate = sc.nextLine();
															
				System.out.print("신규 회원 포인트: ");
				String point = sc.nextLine();
				
				mc.insertMenu(menu, memId, nickname, signUpDate, point);
				return;
				
			default: 
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
			
			
		}
	} // insertMenu end
	
	
	public void displaySuccess(String message) {
		System.out.println("\n 처리결과: " + message);
	} // displaySuccess end
	
	public void displayFail(String message) {
		System.out.println("\n 처리결과: " + message);
	}// displayFail end
} // class end
