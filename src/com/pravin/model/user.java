package com.pravin.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class user {

	@Id
    @Column(name="user_id")
	private int user_id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="active")
	private String active;
	@Column(name="email")
	private String email;
	@Column(name="authority")
	private String authority;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
