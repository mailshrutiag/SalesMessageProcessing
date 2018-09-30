package com.sales.message.processing.model.sales;

public class Sale {
	private String productType;
	private int value;

	public Sale() {	}

	public Sale(String productType, int value) {
		this.setProductType(productType);
		this.setValue(value);
	}

	public Sale(Sale sale) {
		this.productType = sale.getProductType();
		this.value = sale.getValue();
	}	
	
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void addValue(int changeValue) {
		this.value += changeValue;
	}

	public void subValue(int changeValue) {
		this.value = (this.value - changeValue) < 0 ? 0 : this.value - changeValue;		//sale value cannot be negative
	}

	public void multValue(int changeValue) {
		this.value *= changeValue;
	}
}
