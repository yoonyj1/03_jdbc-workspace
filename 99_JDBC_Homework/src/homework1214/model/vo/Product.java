package homework1214.model.vo;

import java.sql.Date;

public class Product {

	private int pno;
	private String pName;
	private int price;
	private Date regDate;
	
	public Product() {}

	public Product(int pno, String pName, int price, Date regDate) {
		this.pno = pno;
		this.pName = pName;
		this.price = price;
		this.regDate = regDate;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return pno + ", " + pName + ", " + price + ", " + regDate;
	}
	
	
	
}
