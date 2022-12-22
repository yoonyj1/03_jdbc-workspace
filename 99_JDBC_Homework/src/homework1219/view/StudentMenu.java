package homework1219.view;

import java.util.ArrayList;
import java.util.Scanner;

import homework1219.controller.StudentController;
import homework1219.model.vo.Student;

public class StudentMenu {

	Scanner sc = new Scanner(System.in);
	
	StudentController scon = new StudentController();

	public void mainMenu() {
		while (true) {
			System.out.println(" ===== 학생 정보 관리 프로그램 =====");
			System.out.println("1. 학생 추가");
			System.out.println("2. 전체 학생 조회");
			System.out.println("3. 학생 정보 수정");
			System.out.println("4. 학생 정보 삭제");
			System.out.println("0. 종료");

			System.out.print(">> ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1: // 학생 추가
				Student s = insertMenu();
				scon.insertMenu(s);
				break;
				
			case 2: // 전체 학생 조회
				scon.selectMenu();
				break;
				
			case 3: // 학생 정보 수정
				updateMenu();
				break;
				
			case 4: // 학생 정보 삭제
				String studentNo = deleteMenu();
				scon.deleteMenu(studentNo);
				break;
				
			case 0:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				return;
			}
		}
	} // mainMenu end

	public Student insertMenu() {
		
		System.out.print("학번: ");
		String studentNo = sc.nextLine();
		
		System.out.print("과번호: ");
		String departmentNo = sc.nextLine();
		
		System.out.print("이름: ");
		String studentName = sc.nextLine();
				
		System.out.print("주민번호(- 포함): ");
		String studentSsn = sc.nextLine();
		
		System.out.print("주소: ");
		String studentAddress = sc.nextLine();
		
		Student s = new Student(studentNo, departmentNo, studentName, studentSsn, studentAddress);
		
		return s;
	}
	
	public void updateMenu() {
		System.out.print("수정할 학생의 학번: ");
		String studentNo = sc.nextLine();
		
		System.out.println("수정할 항목");
		System.out.println("1. 과번호");
		System.out.println("2. 주소");
		System.out.print(" >> ");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
		case 1:
			System.out.print("과번호: ");
			String departmentNo = sc.nextLine();
			scon.updateMenu(menu, studentNo, departmentNo);
			return;
			
		case 2:
			System.out.print("주소: ");
			String studentAddress = sc.nextLine();
			scon.updateMenu(menu, studentNo, studentAddress);
			return;

		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			break;
		}
		
	}
	
	public String deleteMenu() {
		System.out.print("삭제할 학생의 학번: ");
		return sc.nextLine();
	}
	
	
	public void displaySuccess(String message) {
		System.out.println("\n처리 성공: " + message);
	}
	
	public void displayFail(String message) {
		System.out.println("\n처리 실패: " + message);
	}
	
	public void displayNoData(String message) {
		System.out.println("\n처리 실패: " + message);
	}
	
	public void displayList(ArrayList<Student> list) {
		System.out.println("\n조회결과\n");
		for (Student s : list) {
			System.out.println(s);
		}
	}
}// class end
