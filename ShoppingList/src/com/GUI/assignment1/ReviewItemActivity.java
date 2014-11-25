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
		
		if (budgetDifference() >= 0)
		{
			tv1.setText("UnderBudget, Good Job");
		}
		else 
		{
			ArrayList<Item> returns = findRemovableItems(Math.abs(budgetDifference()));
			tv1.setText(Integer.toString(returns.size()));
		}
		
		
	}
	
	protected double budgetDifference()
	{
		double total = 0;
		for(Item item : chosenItems)
		{
			total += item.getPrice();
		}
		
		return user.getMoney() - total;
	}
	
	protected ArrayList<Item> findRemovableItems(double overSpend)
	{
		ArrayList<Item> removable = new ArrayList<>();
		Item i = null;
		
		for (Item item : chosenItems)
		{
			if (item.getPrice() > overSpend)
			{
				i = item;
			}
		}
		
		if(!(i.equals(null)))
		{
			removable.add(i);
			return removable;
		}
		
		while (overSpend > 0)	// Creates a list of most expensive items chosen and will return these as suggestions to remove
		{
			i = chosenItems.get(0);
			for (Item item : chosenItems)
			{
				if (item.getPrice() > i.getPrice() && !(removable.contains(item)))
				{
					i = item;
				}
			}
			removable.add(i);
			overSpend -= i.getPrice();
		}
		
		return removable;
	}

}
