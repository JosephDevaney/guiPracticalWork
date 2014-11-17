package com.GUI.assignment1;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable
{
	private String name;
	
	private double price;
	
	private int amount;

	public String getName() {
		return name;
	}

	public Item()
	{
		
	}
	
	private Item(Parcel in) 
	{
		// TODO Auto-generated constructor stub
		setName(in.readString());
		setPrice(in.readDouble());
		setAmount(in.readInt());
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

	/**
	 * Parcelable Functionality taken from Android API Documentation at:
	 * http://developer.android.com/reference/android/os/Parcelable.html
	 * 
	 */
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) 
	{
		// TODO Auto-generated method stub
		out.writeString(getName());
		out.writeDouble(price);
		out.writeInt(amount);
	}
	
	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() 
	{

		@Override
		public Item createFromParcel(Parcel in) 
		{
			// TODO Auto-generated method stub
			return new Item(in);
		}

		@Override
		public Item[] newArray(int size) 
		{
			// TODO Auto-generated method stub
			return new Item[size];
		}
	};

}
