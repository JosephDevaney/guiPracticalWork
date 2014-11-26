package com.GUI.assignment1;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable
{
	private String name;
	
	private String gender;
	
	private int age;
	
	private String job;
	
	private double money;
	
	private String email;
	
	public User()
	{
		
	}
	
	public User(String n, String g, int a, String j, double m, String e)
	{
		setName(n);
		setGender(g);
		setAge(a);
		setJob(j);
		setMoney(m);
		setEmail(e);
	}

	/**
	 * Parcelable Functionality taken from Android API Documentation at:
	 * http://developer.android.com/reference/android/os/Parcelable.html
	 * 
	 * Parcelable Constructor
	 * Gets data from parcel object on a FIFO basis
	 */
	public User(Parcel in) 
	{
		setName(in.readString());
		setGender(in.readString());
		setAge(in.readInt());
		setJob(in.readString());
		setMoney(in.readDouble());
		setEmail(in.readString());
	}
	//End Parcelable Constructor Reference

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Parcelable Functionality taken from Android API Documentation at:
	 * http://developer.android.com/reference/android/os/Parcelable.html
	 * 
	 */
	
	@Override
	public int describeContents() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) //Write data to the parcel object. Works on FIFO basis
	{
		// TODO Auto-generated method stub
		out.writeString(getName());
		out.writeString(getGender());
		out.writeInt(getAge());
		out.writeString(getJob());
		out.writeDouble(getMoney());
		out.writeString(getEmail());
	}
	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() 
	{
		@Override
		public User createFromParcel(Parcel in) 
		{
			// TODO Auto-generated method stub
			return new User(in);
		}

		@Override
		public User[] newArray(int size) 
		{
			// TODO Auto-generated method stub
			return new User[size];
		}
	};
	//End Reference Parcelable
}
