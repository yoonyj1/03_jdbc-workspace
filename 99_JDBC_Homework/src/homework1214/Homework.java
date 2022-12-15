package homework1214;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Homework {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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

		try {
			// java driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// Statement 객체 생성
			stmt = conn.createStatement();

			// 반복문 실행
			while (true) {
				System.out.println("================= 메뉴 선택 ====================");
				System.out.println("1. SELECT문");
				System.out.println("2. INSERT문");
				System.out.println("3. DELETE문");
				System.out.println("4. 종료");
				System.out.print("메뉴 선택: ");
				int menu = sc.nextInt();
				sc.nextLine();

				switch (menu) {
				case 1: // SELECT
					dml = false;
					sql = "SELECT * FROM PRODUCT ORDER BY PNO";

					rset = stmt.executeQuery(sql);
					System.out.println("==================== 조회 결과 ======================");
					break;

				case 2: // INSERT
					dml = true;
					type = "INSERT";
					System.out.print("제품이름: ");
					pname = sc.nextLine();

					System.out.print("가격: ");
					price = sc.nextInt();
					sc.nextLine();

					sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, " + "'" + pname + "'" + ", " + price
							+ ", " + "DEFAULT" + ")";
					break;

				case 3: // DELETE
					dml = true;
					type = "DELETE";
					System.out.print("삭제할 물품: ");
					pname = sc.nextLine();
					

					sql = "DELETE FROM PRODUCT WHERE PNAME LIKE '%" + pname + "%'";
					break;

				case 4:
					System.out.println("프로그램을 종료합니다.");
					sc.close();
					return;
					
				}
				if(dml) {
					result = stmt.executeUpdate(sql);
					
					if (result > 0) {
						conn.commit();
						System.out.println(type +" 완료");
					} else {
						conn.rollback();
						System.out.println(type + " 실패. 다시 한번 확인하세요");
					}
				} else {
					rset = stmt.executeQuery(sql);
					while (rset.next()) {
						int pno = rset.getInt("PNO");
						pname = rset.getString("PNAME");
						price = rset.getInt("PRICE");
						Date regDate = rset.getDate("REG_DATE");

						System.out.println(pno + ", " + pname + ", " + price + ", " + regDate);
					}
				}
			}

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
			} catch (NullPointerException e) {
				System.out.println();
			}
		}

	}

}
