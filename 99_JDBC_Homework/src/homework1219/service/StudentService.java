package homework1219.service;

import static homework1219.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import homework1219.model.dao.StudentDao;
import homework1219.model.vo.Student;

public class StudentService {

	public int insertMenu(Student s) {
		Connection conn = getConnection();
		
		int result = new StudentDao().insertMenu(conn, s);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public ArrayList<Student> selectMenu() {
		Connection conn = getConnection();
		
		ArrayList<Student> list = new StudentDao().selectMenu(conn);
		
		return list;
	}
	
	public int updateMenu(int menu, Student s) {
		Connection conn = getConnection();
		
		int result = new StudentDao().updateMenu(conn, menu, s);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int deleteMenu(String studentName) {
		Connection conn = getConnection();
		
		int result = new StudentDao().deleteMenu(conn, studentName);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
