package com.student.entity;

public class RuleBean {
	// "field":"tel","op":"eq","data":"15632695088"}

	private String field;
	private String op;
	private String data;

	public RuleBean() {
		// TODO Auto-generated constructor stub
	}

	public RuleBean(String field, String op, String data) {
		super();
		this.field = field;
		this.op = op;
		this.data = data;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
