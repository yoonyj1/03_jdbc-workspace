package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {
	
	Scanner sc = new Scanner(System.in);
	ProductController pc = new ProductController();

	public void mainMenu() {
		while (true) {
			System.out.println("\n1. 전체 조회 하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 상품 수정 하기");
			System.out.println("4. 상품 삭제 하기");
			System.out.println("5. 상품 검색 하기");
			System.out.println("0. 프로그램 종료하기");
			
			System.out.print(">> ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				pc.selectProduct();
				break;
				
			case 2:
				insertProduct();
				break;
				
			case 3:
				updateProduct();
				break;
				
			case 4:
				deleteProduct();
				break;
				
			case 5:
				selectByProductName();
				break;
				
			case 0:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				return;
			} // switch end
			
		} // while end
		
	} // mainMenu end
	
	public void insertProduct() {
		String productId = inputId("추가할");
		
		
		String pName = inputpName("추가할");
		
		System.out.print("가격: ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("상품상세정보: ");
		String description = sc.nextLine();
		
		System.out.print("재고수량: ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		Product p = new Product(productId, pName, price, description, stock);
		
		pc.insertProduct(p);
	}
	
	public void updateProduct() {
		pc.selectProduct();
		
		String productId = inputId("수정할");
		
		System.out.println("=== 바꿀 항목 ===");
		System.out.println("1. 가격");
		System.out.println("2. 상품상세정보");
		System.out.println("3. 재고수량");
		System.out.print(">> ");
		
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
		case 1:
			System.out.print("바꿀 가격: ");
			int updatePrice = sc.nextInt();
			sc.nextLine();
			pc.updateProduct(menu, productId, updatePrice);
			return;
			
		case 2:
			System.out.print("상품상세정보 입력: ");
			String updateDes = sc.nextLine();
			pc.updateProduct(productId, updateDes);
			return;
			
		case 3:
			System.out.print("재고수량: ");
			int updateStock = sc.nextInt();
			sc.nextLine();
			pc.updateProduct(menu, productId, updateStock);
			return;
					
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			break;
		}
	}
	
	public void deleteProduct() {
		pc.selectProduct();
		
		String productId = inputId("삭제할");
		
		pc.deleteProduct(productId);
	}
	
	public void selectByProductName() {
		String keyword = inputpName("검색할");
		
		pc.selectByProductName(keyword);
	}
	

	public void displayNoData(String message) {
		System.out.println("\n처리 결과 : " + message);
	}
	
	public void displayList(ArrayList<Product> list) {
		System.out.println("\n조회 결과");
		
		for (Product p : list) {
			System.out.println(p);
		}
	}
	
	public void displaySuccess(String message) {
		System.out.println("\n처리 결과 : " + message);
	}
	
	public void displayFail(String message) {
		System.out.println("\n처리 결과 : " + message);
	}
	
	public String inputId(String type) {
		System.out.println(type + "상품 아이디: ");
		String productId = sc.nextLine();
		
		return productId;
	}
	
	public String inputpName(String type) {
		System.out.println(type + "상품명: ");
		String pName = sc.nextLine();
		
		return pName;
	}
	
} // class end
