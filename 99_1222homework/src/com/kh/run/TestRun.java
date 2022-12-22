package com.kh.run;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class TestRun {

	public static void main(String[] args) {
		Properties prop = new Properties();
		
		try {
			prop.store(new FileOutputStream("resources/driver.properties"), "driver");
			prop.store(new FileOutputStream("resources/query.xml"), "query");
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // main 끝

} // 클래스 끝
