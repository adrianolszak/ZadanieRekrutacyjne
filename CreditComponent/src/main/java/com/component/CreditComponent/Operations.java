package com.component.CreditComponent;

public class Operations {
	private String operation;
	private String date;
	private double value;
	
	public Operations(String operation, String date, double value) {
		this.operation = operation;
		this.date = date;
		this.value = value;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
}
