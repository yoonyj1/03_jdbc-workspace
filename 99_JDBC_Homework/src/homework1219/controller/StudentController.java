package homework1219.controller;

import java.util.ArrayList;

import homework1219.model.dao.StudentDao;
import homework1219.model.vo.Student;
import homework1219.view.StudentMenu;

public class StudentController {

	int result = 0;
	
	public void insertMenu(Student s) {
		
		result = new StudentDao().insertMenu(s);
		
		if (result > 0) {
			new StudentMenu().displaySuccess("추가 성공");
		} else {
			new StudentMenu().displayFail("추가 실패, 값을 확인하세요");
			
		}
	}
	
	public void selectMenu() {
		ArrayList<Student> list = new StudentDao().selectMenu();
		
		if(list.isEmpty()) {
			new StudentMenu().displayNoData("조회 결과 없음");
		} else{
			new StudentMenu().displayList(list);
		}
		
	}
	
	public void updateMenu(int menu, String studentName , String info) {
		Student s = new Student();
		if (menu == 1) {
			s.setStudentName(studentName);
			s.setDepartmentNo(info);
			result = new StudentDao().updateMenu(menu, s);
		} else if (menu == 2) {
			s.setStudentName(studentName);
			s.setStudentAddress(info);
			result = new StudentDao().updateMenu(menu, s);
		}
		
	
		if (result > 0) {
			new StudentMenu().displaySuccess("업데이트 성공");
		} else {
			new StudentMenu().displayFail("업데이트 실패, 값을 확인하세요");
		}
	}
	
	public void deleteMenu(String studentName) {
		result = new StudentDao().deleteMenu(studentName);
		
		if (result > 0) {
			new StudentMenu().displaySuccess("삭제 성공");
		} else {
			new StudentMenu().displayFail("삭제 실패, 값을 확인하세요");
		}
	}
}
