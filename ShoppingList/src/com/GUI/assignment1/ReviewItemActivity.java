package com.GUI.assignment1;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
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
		
		user = getIntent().getExtras().getParcelable("user");
		chosenItems = getIntent().getParcelableArrayListExtra("chosenItems");
		
		TableLayout table = (TableLayout) findViewById(R.id.review_items);
		
		DecimalFormat df = new DecimalFormat("€###0.00");
		
		for (Item item : chosenItems)
		{
			int amount;
			double price;
			double vatAmt;
			double totalPrice;
			
			amount = item.getAmount();
			price = item.getPrice();
			totalPrice = amount * price;
			vatAmt = totalPrice * 0.21;
			
			/**
			 * Used examples from android api documentation and StackOverflow when researching how to create
			 * Views dynamically.
			 * https://developer.android.com/guide/topics/ui/layout/grid.html
			 * http://androidexample.com/Table_Layout_-_Android_Example/index.php?view=article_discription&aid=74&aaid=98
			 * http://stackoverflow.com/questions/1851633/how-to-add-button-dynamically-in-android
			 * 
			 */
			
			TableRow row = new TableRow(this);
			
			TextView name = new TextView(this);
			name.setText(item.getName());
			row.addView(name);
			
			TextView qty = new TextView(this);
			qty.setText(Integer.toString(amount));
			row.addView(qty);
			
			TextView preVat = new TextView(this);
			preVat.setText(df.format(totalPrice - vatAmt));
			row.addView(preVat);
			
			TextView vat = new TextView(this);
			vat.setText(df.format(vatAmt));
			row.addView(vat);
			
			TextView totalCost = new TextView(this);
			totalCost.setText(df.format(totalPrice));
			row.addView(totalCost);
			
			table.addView(row);
		}
		
		
	}
	
	
	
	
	

}
