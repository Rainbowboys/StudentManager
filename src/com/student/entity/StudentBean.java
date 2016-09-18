package com.student.entity;

import org.bson.types.ObjectId;

public class StudentBean {
	private String username;
	private String password;
	private String tel;
//	private ObjectId _id;
	private String id;
	

	public StudentBean() {
		// TODO Auto-generated constructor stub
	}

	public StudentBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public StudentBean(String username, String password, String tel) {
		super();
		this.username = username;
		this.password = password;
		this.tel = tel;
	}
	



//	public ObjectId get_id() {
//		return _id;
//	}
//
//	public void set_id(ObjectId _id) {
//		this._id = _id;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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
