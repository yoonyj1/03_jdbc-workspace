package homework1214.view;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import homework1214.controller.ProductController;

public class ProductMenu {
	Scanner sc = new Scanner(System.in);
	ProductController pc = new ProductController();

	public void mainMenu() {
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
			case 1:
				select();
				break;

			case 2:
				pc.insert();
				break;

			case 3:
				pc.delete();
				break;

			case 4:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				return;
			}
		}
	}
	public void select() {
		System.out.println("==================== 조회 결과 ======================");
		pc.select();
		System.out.println(pno + ", " + pname + ", " + price + ", " + regDate);
	}

} // 클래스 끝
