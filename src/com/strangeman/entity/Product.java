package com.strangeman.entity;

public class Product {
	private String productId;
	private String productName;
	private float price;
	private String imagePath;
	public Product(String productId, String productName, String imagePath) {
		this.productId = productId;
		this.productName = productName;
		this.imagePath = imagePath;
	}
	public Product(String productId, String productName, float price, String imagePath) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.imagePath = imagePath;
	}
	public String getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public float getPrice() {
		return price;
	}
	public String getImagePath() {
		return imagePath;
	}
}
