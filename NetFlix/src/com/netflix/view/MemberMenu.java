package com.netflix.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.netflix.controller.MemberController;
import com.netflix.model.vo.Member;

public class MemberMenu {

	Scanner sc = new Scanner(System.in);
	MemberController mc = new MemberController();
	
	int menu = 0;
	String memId = null;
	String nickname = null;
	String signUpDate = null;
	String point = null;

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
			menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				insertMenu();
				break;

			case 2:
				mc.selectMenu();
				break;

			case 3:
				selectInfo();
				break;

			case 4:
				mc.selectMenu();
				updateMenu();
				break;

			case 5:
				mc.selectMenu();
				deleteMenu();
				break;

			case 6:
				System.out.println("시스템을 종료합니다");
				sc.close();
				return;
				
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;

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
			
			menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1: case 2: case 3:
				System.out.print("신규 회원 ID(7자 이내): ");
				memId = sc.nextLine();
				
				System.out.print("신규 회원 닉네임(4자 이내): ");
				nickname = sc.nextLine();
				
				System.out.print("신규 회원 가입일(951022 형식으로): ");
				signUpDate = sc.nextLine();
															
				System.out.print("신규 회원 포인트: ");
				point = sc.nextLine();
				
				mc.insertMenu(menu, memId, nickname, signUpDate, point);
				return;
				
			default: 
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
			
			
		}
	} // insertMenu end
	
	/**
	 * 3. 사용자가 입력한 ID 혹은 닉네임으로 회원정보 검색하는 화면
	 */
	public void selectInfo() {
		System.out.println("1. ID로 검색");
		System.out.println("2. 닉네임으로 검색");
		System.out.print(">> ");
		menu = sc.nextInt();
		sc.nextLine();
		
		if(menu == 1) {
			System.out.print("검색할 ID 입력: ");
			memId = sc.nextLine();
			
			new MemberController().selectInfo(menu, memId);
		} else if(menu == 2) {
			System.out.print("검색할 닉네임 입력: ");
			nickname = sc.nextLine();
			
			new MemberController().selectInfo(menu, nickname);
		} else {
			System.out.println("잘못 입력하셨습니다. 초기화면으로 돌아갑니다.");
			return;
		}
	}
	
	/**
	 * 4. 유저 정보를 수정할 때 사용하는 화면
	 */
	public void updateMenu() {
		System.out.print("수정할 회원의 아이디 입력: ");
		memId = sc.nextLine();
		
		System.out.print("수정할 닉네임: ");
		nickname = sc.nextLine();
		
		System.out.print("수정할 포인트: ");
		point = sc.nextLine();
		
		mc.updateMenu(memId, nickname, point);
	}
	
	/**
	 * 5. 유저 정보 삭제 시 사용하는 화면
	 */
	public void deleteMenu() {
		System.out.print("삭제할 회원의 ID 입력: ");
		memId = sc.nextLine();
		
		mc.deleteMenu(memId);
	}
	
	/**
	 * insert 또는 update가 성공적일 때 뜨는 화면
	 * @param message
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 처리결과: " + message);
	} // displaySuccess end
	
	/**
	 * insert 또는 update에 실패할 시 뜨는 화면
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n 처리결과: " + message);
	}// displayFail end
	
	/**
	 * select 결과가 없을 때 뜨는 화면
	 * @param message
	 */
	public void displayNoData(String message) {
		System.out.println("\n 처리결과: " + message);
	} // displayNoDate end
	
	/**
	 * select 결과가 존재할 때 나오는 화면
	 * @param list
	 */
	public void displayMember(ArrayList<Member> list) {
		System.out.println("\n 조회 결과");
		System.out.println("아이디\t등급\t닉네임\t가입일\t\t포인트");
		for (Member m : list) {
			System.out.println(m);
		} // displayMember end
	}
} // class end
