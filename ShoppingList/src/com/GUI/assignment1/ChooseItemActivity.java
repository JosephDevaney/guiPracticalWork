package com.GUI.assignment1;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooseItemActivity extends ListActivity
{
	public class CustomAdapter extends ArrayAdapter<String> 
	{
		Context context;
	    String[] data;
	    //Item[] items;
	    ArrayList<Item> items;
	    ArrayList<Item> chosenItems;
	    private LayoutInflater inflater = null;

		@SuppressWarnings("unchecked")
		public CustomAdapter(Context context, int resource, int textViewResourceId, String[] data) 
		{
			super(context, resource, textViewResourceId, data);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.data = data;
			
			items = new ArrayList<>();
			for (int i = 0; i < data.length; i++)
			{
				Item it = new Item();
				it.setName(data[i]);
				it.setPrice(4.99);
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


			TextView item = (TextView) v.findViewById(R.id.item);
			TextView price = (TextView) v.findViewById(R.id.price);
			Button plus = (Button) v.findViewById(R.id.plus);
			EditText amount = (EditText) v.findViewById(R.id.num);
			Button minus = (Button) v.findViewById(R.id.minus);

			item.setText(items.get(pos).getName());
			price.setText(Double.toString(items.get(pos).getPrice()));	//make random
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
		
		String[] itemList = getResources().getStringArray(R.array.itemList);
		CustomAdapter days = new CustomAdapter(this, R.layout.row, R.id.item, itemList);
		setListAdapter(days);
		
	}

}
