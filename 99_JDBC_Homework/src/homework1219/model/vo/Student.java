package homework1219.model.vo;

import java.sql.Date;

public class Student {

	// 필드부
	private String studentNo; 			// 학번
	private String departmentNo;		// 과번호
	private String studentName;			// 이름
	private String studentSsn;			// 주민번호
	private String studentAddress;		// 주소
	private Date entranceDate;			// 입학일
	private String absenceYN;			// 휴학여부
	private String coachProfessorNo;	// 지도교수번호
	
	// 생성자부
	public Student() {}
	
	
	
	public Student(String studentNo, String departmentNo, String studentName, String studentSsn,
			String studentAddress) {
		super();
		this.studentNo = studentNo;
		this.departmentNo = departmentNo;
		this.studentName = studentName;
		this.studentSsn = studentSsn;
		this.studentAddress = studentAddress;
	}



	public Student(String studentNo, String departmentNo, String studentName, String studentSsn, String studentAddress,
			Date entranceDate, String absenceYN, String coachProfessorNo) {
		super();
		this.studentNo = studentNo;
		this.departmentNo = departmentNo;
		this.studentName = studentName;
		this.studentSsn = studentSsn;
		this.studentAddress = studentAddress;
		this.entranceDate = entranceDate;
		this.absenceYN = absenceYN;
		this.coachProfessorNo = coachProfessorNo;
	}

	// 메소드부
	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSsn() {
		return studentSsn;
	}

	public void setStudentSsn(String studentSsn) {
		this.studentSsn = studentSsn;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	public String getAbsenceYN() {
		return absenceYN;
	}

	public void setAbsenceYN(String absenceYN) {
		this.absenceYN = absenceYN;
	}

	public String getCoachProfessorNo() {
		return coachProfessorNo;
	}

	public void setCoachProfessorNo(String coachProfessorNo) {
		this.coachProfessorNo = coachProfessorNo;
	}

	@Override
	public String toString() {
		return studentNo + ", " + departmentNo + ", " + studentName	+ ", " + studentSsn + ", " + studentAddress + ", " + entranceDate
				+ ", " + absenceYN + ", " + coachProfessorNo;
	}
	
	
	
	
}
