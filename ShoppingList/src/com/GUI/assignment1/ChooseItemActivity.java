package com.GUI.assignment1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseItemActivity extends ListActivity
{
	User user;
    ArrayList<Item> items;
    ArrayList<Item> chosenItems;
    
	public class CustomAdapter extends ArrayAdapter<String> 
	{
		Context context;
	    String[] data;
	    private LayoutInflater inflater = null;

		public CustomAdapter(Context context, int resource, int textViewResourceId, String[] data) 
		{
			super(context, resource, textViewResourceId, data);
			// TODO Auto-generated constructor stub
			this.context = context;
			user = getIntent().getExtras().getParcelable("user");
			this.data = data;
			Random r = new Random();
			
			items = new ArrayList<>();
			for (int i = 0; i < data.length; i++)
			{
				Item it = new Item();
				it.setName(data[i]);
				it.setPrice(r.nextDouble() * 20);
				it.setAmount(0);
				items.add(it);
			}
			
			chosenItems = new ArrayList<>();
			
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(final int pos, View convertView, ViewGroup parent)
		{
			View v = convertView;
			if (v == null)
			{
				v = inflater.inflate(R.layout.row, parent, false);
			}
			
			/**
			 * Decimal Format examples used to format price output.
			 * Examples taken from StackOverFlow and Android Developer API documentation
			 * http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
			 * http://developer.android.com/reference/java/text/DecimalFormat.html
			 */
			DecimalFormat df = new DecimalFormat("€##0.00");

			TextView item = (TextView) v.findViewById(R.id.item);
			TextView price = (TextView) v.findViewById(R.id.price);
			Button plus = (Button) v.findViewById(R.id.plus);
			EditText amount = (EditText) v.findViewById(R.id.num);
			Button minus = (Button) v.findViewById(R.id.minus);

			item.setText(items.get(pos).getName());
			price.setText(df.format(items.get(pos).getPrice()));
			plus.setOnClickListener(new OnClickListener() {

				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Item i = items.get(pos);
					int amt = i.getAmount();
					if (amt == 0) 
					{
						chosenItems.add(i);
					}
					amt++;
					i.setAmount(amt);
					notifyDataSetChanged();
			
				}
			});

			amount.setText(Integer.toString(items.get(pos).getAmount()));
			amount.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable s)
				{
					// TODO Auto-generated method stub
					int num;
					String amt = s.toString();
					Item i = items.get(pos);
					
					try
					{
						num = Integer.parseInt(amt);
					} 
					catch (NumberFormatException e)
					{
						num = 1;
					}
					
					if (num > 0 && !(chosenItems.contains(i)))
					{
						chosenItems.add(i);
					}
					else if (num == 0 && chosenItems.contains(i))
					{
						chosenItems.remove(i);
					}
					i.setAmount(num);
					//notifyDataSetChanged();
				}
			});
			
			minus.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Item i = items.get(pos);
					int amt = i.getAmount();
					
					if (amt > 0)
					{
						if (amt == 1) 
						{
							chosenItems.remove(i);
						}
						i.setAmount(--amt);
						notifyDataSetChanged();
					}					
				}
			});
			return v;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_items);
		
		Button submit = (Button) findViewById(R.id.submitItems);
		
		String[] itemList = getResources().getStringArray(R.array.itemList);
		CustomAdapter days = new CustomAdapter(this, R.layout.row, R.id.item, itemList);
		setListAdapter(days);
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				double moneyDiff = budgetDifference();
				if (moneyDiff >= 0)
				{
					Intent review = new Intent(ChooseItemActivity.this, ReviewItemActivity.class);
					review.putParcelableArrayListExtra("chosenItems", chosenItems);
					review.putExtra("user", user);
					startActivity(review);
				}
				else
				{
					DecimalFormat df = new DecimalFormat("€###0.00");
					String message = getString(R.string.spentTooMuch) + " " + df.format(Math.abs(moneyDiff));
					Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();;
				}
				
				
			}
		});
		
		
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View view, int pos, long id)
	{
		
	}
	
	
	protected double budgetDifference()
	{
		double total = 0;
		for(Item item : chosenItems)
		{
			total += (item.getPrice() * item.getAmount());
		}
		
		return user.getMoney() - total;
	}

}
