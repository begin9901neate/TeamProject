package com.example.demo.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class UserVO {
	
	private Integer id;
	private String email;
	private String password;
	
	private String chinesName;
	
	private String englishName;
	
	private String cellphone;
	private String address;
	private int age;
	private int gender;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private Date deletedAt;
		
	private Integer permission_id;

	private String salt;
	
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChinesName() {
		return chinesName;
	}

	public void setChinesName(String chinesName) {
		this.chinesName = chinesName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Integer getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	} 
	
	
}
