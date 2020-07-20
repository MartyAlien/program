package com.program.user;

public class User {
	private String ID;	// 用户ID：主键
	private String userName;	//用户名：登录用
	private String userPassword;	//用户密码：登录用
	private String name;	//用户姓名
	private String phone;	//用户手机
	private String userType;	//用户类型：常住、租户
	private double advDeposit;	//用户预存款
	private String palce;	//门牌地址
	private String available;	//是否可用
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String ID, String userName, String userPassword, String name, String phone, String userType,
			double advDeposit, String palce, String available) {
		super();
		this.ID = ID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.name = name;
		this.phone = phone;
		this.userType = userType;
		this.advDeposit = advDeposit;
		this.palce = palce;
		this.available = available;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public double getAdvDeposit() {
		return advDeposit;
	}
	public void setAdvDeposit(double advDeposit) {
		this.advDeposit = advDeposit;
	}
	public String getPalce() {
		return palce;
	}
	public void setPalce(String palce) {
		this.palce = palce;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "User [ID=" + ID + ", userName=" + userName + ", userPassword=" + userPassword + ", name=" + name
				+ ", phone=" + phone + ", userType=" + userType + ", advDeposit=" + advDeposit + ", palce=" + palce
				+ ", available=" + available + "]";
	}
	
}
