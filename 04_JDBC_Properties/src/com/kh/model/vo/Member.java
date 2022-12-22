package com.kh.model.vo;

import java.sql.Date;

// VO(Value Object)
// 한 명의 회원(DB테이블의 한 행의 데이터들)에 대한 데이터들을 기록할 수 있는 저장용 객체
public class Member {

	// 필드부
	private int userNo;			// 회원번호
	private String userId;		// 회원아이디
	private String userPwd; 	// 비밀번호
	private String userName;	// 회원명
	private String gender;		// 성별
	private int age;			// 나이
	private String email;		// 이메일
	private String phone;		// 전화번호
	private String address;		// 주소
	private String hobby;		// 취미
	private Date enrollDate;	// 등록일자
	
	// 생성자부
	public Member() {}

	public Member(int userNo, String userId, String userPwd, String userName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	public Member(String userId, String userPwd, String userName, String gender, int age, String email, String phone,
			String address, String hobby) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}

	// 메소드부
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return userNo + ", " + userId + ", " + userPwd + ", " + userName + ", " + gender + ", " + age + ", " + email + ", " + phone + ", "
				+ address + ", " + hobby + ", " + enrollDate;
	}
	
} // class end
