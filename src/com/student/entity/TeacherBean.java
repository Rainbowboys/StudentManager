package com.student.entity;

import org.bson.types.ObjectId;

public class TeacherBean {
	private String name;
	private String telphone;
	private String password;
	private String id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public TeacherBean() {
		// TODO Auto-generated constructor stub
	}

	public TeacherBean(String name, String telphone, String password) {
		super();
		this.name = name;
		this.telphone = telphone;
		this.password = password;
	}

}
