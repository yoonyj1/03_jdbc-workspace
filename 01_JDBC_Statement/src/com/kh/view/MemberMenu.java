package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

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

		while (true) {
			System.out.println("\n   == 회원관리 프로그램 ==");
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│   1. 회원 추가      		│");
			System.out.println("│   2. 회원 전체 조회      		│");
			System.out.println("│   3. 회원 아이디 검색      	│");
			System.out.println("│   4. 회원 이름으로 키워드 검색   	│");
			System.out.println("│   5. 회원 정보 변경      		│");
			System.out.println("│   6. 회원탈퇴      		│");
			System.out.println("│   9. 이름으로 정보 조회    		│");
			System.out.println("│   0. 프로그램 종료     	  	│");
			System.out.println("└───────────────────────────────┘");
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				inputMember();
				break;

			case 2:
				mc.selectList(); // 입력받을 것 없으면 바로 Controller 호출
				break;

			case 3:
				String userId = inputMemberId();
				mc.selectByUserId(userId);
				break;

			case 4:
				break;

			case 5:
				break;

			case 6:
				break;
				
			case 9:
				selectNameInfo();
				break;

			case 0:
				System.out.println("이용해주셔서 감사합니다.");
				return;

			default:
				System.out.println("메뉴를 잘못 입력했습니다. 다시 입력해주세요");
				break;

			} // switch end

		} // while end

	} // mainMenu end

	/**
	 * 1. 회원 추가 화면(서브 화면) 추가하고자 하는 회원의 정보를 입력받아서 회원 추가 요청하는 창
	 */
	public void inputMember() {
		System.out.println("\n=== 회원 추가 ===");

		System.out.print("아이디: ");
		String userId = sc.nextLine();

		System.out.print("비밀번호: ");
		String userPwd = sc.nextLine();

		System.out.print("이름: ");
		String userName = sc.nextLine();

		System.out.print("성별('M' / 'F'): ");
		String gender = sc.nextLine();

		System.out.print("나이: ");
		String age = sc.nextLine(); // 이제 나이도 문자열로 받기 => 웹에서는 다 문자로 넘어옴

		System.out.print("이메일: ");
		String email = sc.nextLine();

		System.out.print("전화번호(- 빼고 입력): ");
		String phone = sc.nextLine();

		System.out.print("주소: ");
		String address = sc.nextLine();

		System.out.print("취미(,로 공백없이 연이어 작성): ");
		String hobby = sc.nextLine();

		// 회원추가요청 == Controller 메소드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);

	}
	
	/**
	 * 3. 사용자에게 회원아이디를 입력 받은 후 입력 된 값을 반환시켜주는 메소드
	 */
	public String inputMemberId() {
		System.out.print("\n회원 아이디 입력: ");
		return sc.nextLine();
	}
	/**
	 * 9. 이름으로 정보 검색하는 화면
	 */
	public void selectNameInfo() {
		System.out.print("이름 입력: ");
		String userName = sc.nextLine();
		
		mc.selectNameInfo(userName);
	}
	
	//-------------------------------------- 응답화면 -----------------------------------------
	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게 될 응답화면
	 * @param message
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공: " + message);
	}

	/**
	 * 서비스 요청 실패 후 사용자가 보게되는 화면
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패: " + message);
	}
	
	/**
	 * 조회 서비스 요청 시 조회 결과가 없을 경우 보게되는 화면
	 * @param message
	 */
	public void displayNoData(String message) {
		System.out.println("\n 서비스 요청 실패: " + message);
	}
	
	/**
	 * 조회 서비스 성공 일 경우 보게되는 화면
	 * @param list
	 */
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다.");
		// 단순 for 문
		// for (int i = 0; i < list.size() - 1; i++) {
		// System.out.println(list.get(i));
		// }
		
		// 향상 된 for 문
		for (Member m : list) {
			System.out.println(m);
		}
	}
	
}// class end
