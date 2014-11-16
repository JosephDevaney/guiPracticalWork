package com.GUI.assignment1;

public class Item {
	private String name;
	
	private double price;
	
	private int amount;

	public String getName() {
		return name;
	}

	public Item()
	{
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
