package test;

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
		
		/*
		Properties prop = new Properties();
		prop.setProperty("C", "INSERT");
		prop.setProperty("R", "SELECT");
		prop.setProperty("U", "UPDATE");
		prop.setProperty("D", "DELETE");
		
		// 순서를 유지하지 않고 막 담김
		try {
			prop.store(new FileOutputStream("resources/test.properties"), "properties Test");
			prop.storeToXML(new FileOutputStream("resources/test.xml"), "properties Test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("driver"));
		System.out.println(prop.getProperty("url"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
		// System.out.println(prop.getProperty("pasword")); 없는 키 값 입력하면 null 반환
		
	} // main 끝

} // 클래스 끝
