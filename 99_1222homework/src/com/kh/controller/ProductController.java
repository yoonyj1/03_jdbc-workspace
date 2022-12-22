package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {

	public void selectProduct() {
		ArrayList<Product> list = new ProductService().selectProduct();
		
		if (list.isEmpty()) {
			new ProductMenu().displayNoData("조회 된 데이터가 없습니다.");
		} else {
			new ProductMenu().displayList(list);
		}
	}
	
	public void insertProduct(Product p) {
		int result = new ProductService().insertProduct(p);
		
		if (result > 0) {
			new ProductMenu().displaySuccess("추가 완료");
		} else {
			new ProductMenu().displayFail("추가 실패");
		}
		
	}
	
	public void updateProduct(int menu, String productId, int updateInt) {
		int result = new ProductService().updateProduct(menu, productId, updateInt);
		
		if (result > 0) {
			new ProductMenu().displaySuccess("수정 완료");
		} else {
			new ProductMenu().displayFail("수정 실패");
		}
	}
	
	public void updateProduct(String productId, String updateDes) {
		int result = new ProductService().updateProduct(productId, updateDes);
		
		if (result > 0) {
			new ProductMenu().displaySuccess("수정 완료");
		} else {
			new ProductMenu().displayFail("수정 실패");
		}
	}
	
	public void deleteProduct(String productId) {
		int result = new ProductService().deleteProduct(productId);
		
		if (result > 0) {
			new ProductMenu().displaySuccess("삭제 완료");
		} else {
			new ProductMenu().displayFail("삭제 실패");
		}
	}
	
	public void selectByProductName(String keyword) {
		ArrayList<Product> list = new ProductService().selectByProductName(keyword);
		
		if (list.isEmpty()) {
			new ProductMenu().displayNoData(keyword + "가 들어간 결과가 없습니다.");
		} else {
			new ProductMenu().displayList(list);
		}
	}
} // class end
