package com.student.entity;

public class TestBean {
	private int id;
	private String name;
	private String ship;
	private String note;
	private String stock;
	private String sdate;

	public TestBean() {
		// TODO Auto-generated constructor stub
	}

	public TestBean(int id, String name, String ship, String note, String stock, String sdate) {
		super();
		this.id = id;
		this.name = name;
		this.ship = ship;
		this.note = note;
		this.stock = stock;
		this.sdate = sdate;
	}

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

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

}
