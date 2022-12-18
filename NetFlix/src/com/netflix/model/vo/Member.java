package com.netflix.model.vo;

import java.sql.Date;

public class Member {

	// 필드부
	private String memId;		// 회원아이디
	private String grade;		// 회원등급
	private String nickname;	// 닉네임
	private Date signUpDate;	// 가입일
	private int point;			// 포인트
	
	// 생성자부
	public Member() {}
	
	
	public Member(String memId, String nickname, Date signUpDate, int point) {
		super();
		this.memId = memId;
		this.nickname = nickname;
		this.signUpDate = signUpDate;
		this.point = point;
	}

	public Member(String memId, String nickname, int point) {
		super();
		this.memId = memId;
		this.nickname = nickname;
		this.point = point;
	}

	
	public Member(String memId) {
		super();
		this.memId = memId;
	}

	public Member(String memId, String grade, String nickname, Date signUpDate, int point) {
		super();
		this.memId = memId;
		this.grade = grade;
		this.nickname = nickname;
		this.signUpDate = signUpDate;
		this.point = point;
	}

	

	// 메소드부
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return memId+ "\t" + grade + "\t" + nickname + "\t" + signUpDate + "\t" + point;
	}
	
	
} // class end
