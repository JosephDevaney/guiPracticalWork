package com.GUI.assignment1;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable
{
	private String name;
	
	private double price;
	
	private int amount;
	
	private String description;

	public String getName() {
		return name;
	}

	public Item()
	{

	}
	
	/**
	 * Parcelable Functionality taken from Android API Documentation at:
	 * http://developer.android.com/reference/android/os/Parcelable.html
	 * 
	 * Parcelable Constructor
	 * Gets data from parcel object on a FIFO basis
	 */
	private Item(Parcel in) 
	{
		// TODO Auto-generated constructor stub
		setName(in.readString());
		setPrice(in.readDouble());
		setAmount(in.readInt());
		setDescription(in.readString());
	}
	//End Reference

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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
	public void writeToParcel(Parcel out, int flags) //Write data to the parcel object. Works on FIFO basis
	{
		// TODO Auto-generated method stub
		out.writeString(getName());
		out.writeDouble(getPrice());
		out.writeInt(getAmount());
		out.writeString(getDescription());
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
	//end Parcelable Reference

}
