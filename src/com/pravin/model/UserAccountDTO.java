package com.pravin.model;

public class UserAccountDTO {

	private int id;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private String age;
	
	private String mobile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserAccountDTO(int id, String userName, String password, String email, String age, String mobile) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.age = age;
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		StringBuffer retStrBuf = new StringBuffer();
		retStrBuf.append("id = " + this.id);
		retStrBuf.append(" , userName = " + this.userName);
		retStrBuf.append(" , password = " + this.password);
		retStrBuf.append(" , email = " + this.email);
		retStrBuf.append(" , age = " + this.age);
		retStrBuf.append(" , mobile = " + this.mobile);
		return retStrBuf.toString();
	}
	
	
}
