package homework1219.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import homework1219.model.vo.Student;

public class StudentDao {

	int result = 0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;
	
	String sql = null;
	
	
	public int insertMenu(Student s) {
		
		sql = "INSERT INTO TB_STUDENT2 VALUES("
				+ "'" + s.getStudentNo() + "', "
				+ "'" + s.getDepartmentNo() + "', "
				+ "'" + s.getStudentName() + "', "
				+ "'" + s.getStudentSsn() + "', "
				+ "'" + s.getStudentAddress() +"', "
				+ null + ", " + null + ", " + null + ")";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WORKBOOK", "WORKBOOK");
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if (result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public ArrayList<Student> selectMenu() {
		ArrayList<Student> list = new ArrayList<Student>();
		
		sql = "SELECT * FROM TB_STUDENT2";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WORKBOOK", "WORKBOOK");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				list.add(new Student(
						rset.getString("STUDENT_NO"),
						rset.getString("DEPARTMENT_NO"),
						rset.getString("STUDENT_NAME"),
						rset.getString("STUDENT_SSN"),
						rset.getString("STUDENT_ADDRESS"),
						rset.getDate("ENTRANCE_DATE"),
						rset.getString("ABSENCE_YN"),
						rset.getString("COACH_PROFESSOR_NO")
						));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int updateMenu(int menu, String studentName, String info) {
		if (menu == 1) {
			sql = "UPDATE TB_STUDENT SET STUDENT_NO = '" + info + "'" + "WHERE STUDENT_NAME = '" + studentName + "'";
		} else if (menu == 2) {
			sql = "UPDATE TB_STUDENT SET DEPARTMENT_NO = '" + info + "'" + "WHERE STUDENT_NAME = '" + studentName + "'";
		} else if (menu == 3) {
			sql = "UPDATE TB_STUDENT SET STUDENT_ADDRESS = '" + info + "'" + "WHERE STUDENT_NAME = '" + studentName + "'";
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WORKBOOK", "WORKBOOK");
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if (result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteMenu(String studentName) {
		sql = "DELETE FROM TB_STUDENT2 WHERE STUDENT_NAME = '" + studentName + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WORKBOOK", "WORKBOOK");
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if (result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
