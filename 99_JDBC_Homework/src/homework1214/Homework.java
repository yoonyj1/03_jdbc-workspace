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
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		int price = 0;
		String sql = null;

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
					sql = "SELECT * FROM PRODUCT ORDER BY PNO";

					rset = stmt.executeQuery(sql);
					System.out.println("==================== 조회 결과 ======================");
					while (rset.next()) {
						int pno = rset.getInt("PNO");
						String pname = rset.getString("PNAME");
						price = rset.getInt("PRICE");
						Date regDate = rset.getDate("REG_DATE");

						System.out.println(pno + ", " + pname + ", " + price + ", " + regDate);
					}
					break;

				case 2: // INSERT
					System.out.print("제품이름: ");
					String pName = sc.nextLine();

					System.out.print("가격: ");
					price = sc.nextInt();
					sc.nextLine();

					sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, " + "'" + pName + "'" + ", " + price
							+ ", " + "DEFAULT" + ")";

					result = stmt.executeUpdate(sql);

					if (result > 0) {
						conn.commit();
						System.out.println("입력 완료");
					} else {
						conn.rollback();
						System.out.println("입력 실패. 다시 한번 확인하세요");
					}
					break;

				case 3: // DELETE
					System.out.print("삭제할 물품번호: ");
					int delNum = sc.nextInt();
					sc.nextLine();

					sql = "DELETE FROM PRODUCT WHERE PNO = " + delNum;

					result = stmt.executeUpdate(sql);
					if (result > 0) {
						conn.commit();
						System.out.println("삭제 완료");
					} else {
						conn.rollback();
						System.out.println("삭제 실패. 다시 한번 확인하세요");
					}
					break;

				case 4:
					System.out.println("프로그램을 종료합니다.");
					sc.close();
					return;
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
