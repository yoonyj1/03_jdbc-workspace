package homework1214.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductController {
	// 변수 셋팅
	// 객체
	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;
	
	// 일반변수
	int result = 0;
	int price = 0;
	String sql = null;
	String type = null;
	String pname = null;
	boolean dml = true;
	
	public void select() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe");
			
			stmt = conn.createStatement();
			
			sql = "SELECT * FROM PRODUCT ORDER BY PNO"; 
			
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int pno = rset.getInt("PNO");
				pname = rset.getString("PNAME");
				price = rset.getInt("PRICE");
				Date regDate = rset.getDate("REG_DATE");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
