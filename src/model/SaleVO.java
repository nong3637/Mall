package model;

public class SaleVO {
	private int custno;
	private String custname;
	private String grade;
	private int sale;
	public int getCustno() {
		return custno;
	}
	public void setCustno(int custno) {
		this.custno = custno;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
}
