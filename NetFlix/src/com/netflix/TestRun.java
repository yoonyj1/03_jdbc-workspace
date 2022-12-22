package com.netflix;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TestRun {

	public static void main(String[] args) {
		// Properties 복습
		/*
		 * * properties 특징
		 *  - Map 계열 컬렉션 (key + value 세트로 담는 특징)
		 *  - key, value 모두 String (문자열)으로 담기
		 *  - setProperty(String key, String value)
		 *  - getProperty(String key): String(value)
		 *  
		 *  - 주로 외부파일(.properties, .xml)로 입출력할 때 사용 => 환경설정 파일 (개발자가 아닌 사람도 읽을 수 있도록 하는 문서들)
		 */
		
		
		Properties prop = new Properties();
		
		try {
			prop.store(new FileOutputStream("resources/driver.properties"), "properties Test");
			prop.storeToXML(new FileOutputStream("resources/query.xml"), "properties Test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	} // main 끝

} // 클래스 끝
