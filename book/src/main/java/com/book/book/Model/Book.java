package com.book.book.Model;

public class Book {
	private int id;
	private String name;
	private String title;
	private String description;
	private int price;
	private int quanlity;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuanlity() {
		return quanlity;
	}
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}
	public Book(int id, String name, String title, String description, int price, int quanlity) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		this.price = price;
		this.quanlity = quanlity;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", title=" + title + ", description=" + description + ", price="
				+ price + ", quanlity=" + quanlity + "]";
	}
	
}
