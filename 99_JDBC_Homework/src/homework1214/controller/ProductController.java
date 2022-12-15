package homework1214.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductController {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;
	int result = 0;
	int price = 0;
	String sql = null;
	
	public void mainMenu() {
		try {
			// java driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// Statement 객체 생성
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 객체 반납
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	public int insertMenu(String pName, int price) {
//		sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, " + "'" + pName + "'" + ", " + price
//				+ ", " + "DEFAULT" + ")";
//		
//		return 
//	}
}
