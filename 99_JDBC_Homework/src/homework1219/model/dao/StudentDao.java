package homework1219.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import static homework1219.common.JDBCTemplate.*;
import homework1219.model.vo.Student;

public class StudentDao {

	int result = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	
	private Properties prop = new Properties();
	
	public StudentDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMenu(Connection conn, Student s) {

		String sql = prop.getProperty("insertMenu");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, s.getStudentNo());
			pstmt.setString(2, s.getDepartmentNo());
			pstmt.setString(3, s.getStudentName());
			pstmt.setString(4, s.getStudentSsn());
			pstmt.setString(5, s.getStudentAddress());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public ArrayList<Student> selectMenu(Connection conn) {
		ArrayList<Student> list = new ArrayList<Student>();
		
		String sql = prop.getProperty("selectMenu");
		
		try {
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return list;
	}
	
	public int updateMenu(Connection conn, int menu, Student s) {
		try {
			
			if (menu == 1) {
				String sql = prop.getProperty("updateMenu1");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, s.getDepartmentNo());
				pstmt.setString(2, s.getStudentNo());
			} else if (menu == 2) {
				String sql = prop.getProperty("updateMenu2");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, s.getStudentAddress());
				pstmt.setString(2, s.getStudentNo());
			} 
			
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteMenu(Connection conn, String studentNo) {
		String sql = prop.getProperty("deleteMenu");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, studentNo);
			
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
