package com.student.entity;

import org.bson.types.ObjectId;

public class AdminBean {
	private String name;
	private String telphone;
	private String password;
	private int isSuper;
	private ObjectId id;

	public AdminBean() {
		// TODO Auto-generated constructor stub
	}
  
	public AdminBean(String name, String telphone, String password, int isSuper, ObjectId id) {
		super();
		this.name = name;
		this.telphone = telphone;
		this.password = password;
		this.isSuper = isSuper;
		this.id = id;
	}

	public AdminBean(String name, String telphone, String password, int isSuper) {
		super();
		this.name = name;
		this.telphone = telphone;
		this.password = password;
		this.isSuper = isSuper;
	}





	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public int getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(int isSuper) {
		this.isSuper = isSuper;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
