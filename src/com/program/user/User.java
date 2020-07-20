package com.program.user;

public class User {
	private String ID;
	private String userName;
	private String userPassword;
	private String name;
	private String phone;
	private String userType;
	private double advDeposit;
	private String palce;
	private String available;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String iD, String userName, String userPassword, String name, String phone, String userType,
			double advDeposit, String palce, String available) {
		super();
		ID = iD;
		this.userName = userName;
		this.userPassword = userPassword;
		this.name = name;
		this.phone = phone;
		this.userType = userType;
		this.advDeposit = advDeposit;
		this.palce = palce;
		this.available = available;
	}
	@Override
	public String toString() {
		return "User [ID=" + ID + ", userName=" + userName + ", userPassword=" + userPassword + ", name=" + name
				+ ", phone=" + phone + ", userType=" + userType + ", advDeposit=" + advDeposit + ", palce=" + palce
				+ ", available=" + available + "]";
	}
	
}
