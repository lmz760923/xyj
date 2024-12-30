package stu01.model;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String password;
	private String email;
	private java.util.Date email_verified_at;
	private String remember_token;
	private java.util.Date created_at;
	private java.util.Date updated_at;
	
	
	public User(int id,String name,String password,String email,Date verified_at,String remember_token,Date created_at,Date updated_at) {
		this.id=id;
		this.name=name;
		this.password=password;
		this.email=email;
		this.email_verified_at=verified_at;
		this.remember_token=remember_token;
		this.created_at=created_at;
		this.updated_at=updated_at;
		
	}
	public User() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public java.util.Date getEmail_verified_at() {
		return email_verified_at;
	}
	public void setEmail_verified_at(java.util.Date email_verified_at) {
		this.email_verified_at = email_verified_at;
	}
	public String getRemember_token() {
		return remember_token;
	}
	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}
	public java.util.Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(java.util.Date created_at) {
		this.created_at = created_at;
	}
	public java.util.Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(java.util.Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public User(String name,String password,String email) {
		this.name=name;
		this.password=password;
		this.email=email;
	}
}
