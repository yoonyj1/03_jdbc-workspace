package homework1219.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import homework1219.model.vo.Student;

public class StudentDao {

	int result = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	String sql = null;
	
	
	public int insertMenu(Student s) {
		
		sql = "INSERT INTO TB_STUDENT2 VALUES(?, ?, ?, ?, ?, null, null, null)";
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WORKBOOK", "WORKBOOK");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getStudentNo());
			pstmt.setString(2, s.getDepartmentNo());
			pstmt.setString(3, s.getStudentName());
			pstmt.setString(4, s.getStudentSsn());
			pstmt.setString(5, s.getStudentAddress());
			
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
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
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int updateMenu(int menu, Student s) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WORKBOOK", "WORKBOOK");
			
			pstmt = conn.prepareStatement(sql);
			
			if (menu == 1) {
				sql = "UPDATE TB_STUDENT2 SET DEPARTMENT_NO = ? WHERE STUDENT_NAME = ?";
				pstmt.setString(1, s.getDepartmentNo());
				pstmt.setString(2, s.getStudentName());
			} else if (menu == 2) {
				sql = "UPDATE TB_STUDENT2 SET STUDENT_ADDRESS = ? WHERE STUDENT_NAME = ?";
				pstmt.setString(1, s.getStudentAddress());
				pstmt.setString(2, s.getStudentName());
			} 
			
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteMenu(String studentName) {
		sql = "DELETE FROM TB_STUDENT2 WHERE STUDENT_NAME = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WORKBOOK", "WORKBOOK");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, studentName);
			
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
