package com.GUI.assignment1;

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
	    Row[] rows;
	    private LayoutInflater inflater = null;

		@SuppressWarnings("unchecked")
		public CustomAdapter(Context context, int resource, int textViewResourceId, String[] data) 
		{
			super(context, resource, textViewResourceId, data);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.data = data;
			
			rows = new Row[data.length];
			
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
					
			
			final TextView item = (TextView) v.findViewById(R.id.item);
			final TextView price = (TextView) v.findViewById(R.id.price);
			final Button plus = (Button) v.findViewById(R.id.plus);
			final EditText amount = (EditText) v.findViewById(R.id.num);
			final Button minus = (Button) v.findViewById(R.id.minus);
			
			/*rows[pos].item = (TextView) v.findViewById(R.id.item);
			rows[pos].price = (TextView) v.findViewById(R.id.price);
			rows[pos].plus = (Button) v.findViewById(R.id.plus);
			rows[pos].amount = (EditText) v.findViewById(R.id.num);
			rows[pos].minus = (Button) v.findViewById(R.id.minus);*/
			

			/*rows[pos].*/item.setText(data[pos]);
			/*rows[pos].*/price.setText("4.99");	//make random
			/*rows[pos].*/plus.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					LinearLayout layout = (LinearLayout)v.getParent();
					
					final EditText amount = (EditText) layout.findViewById(R.id.num);
					
					
					String amt = rows[pos].amount.getText().toString();
					int iAmt = Integer.parseInt(amt);
					iAmt++;
					/*rows[pos].*/amount.setText(Integer.toString(iAmt));
				}
			});
			
			
			/*rows[pos].*/amount.setText("0");
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
