package com.GUI.assignment1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReviewItemActivity extends Activity
{
	User user;
	ArrayList<Item> chosenItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_items);
		
		TextView tv1 = (TextView) findViewById(R.id.reviewText1);
		user = getIntent().getExtras().getParcelable("user");
		chosenItems = getIntent().getParcelableArrayListExtra("chosenItems");
		
		
		
	}
	
	
	
	
	

}
