package com.GUI.assignment1;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChooseItemActivity extends ListActivity
{
	public class CustomAdapter extends ArrayAdapter<String> 
	{
		Context context;
	    String[] data;
	    private LayoutInflater inflater = null;

		@SuppressWarnings("unchecked")
		public CustomAdapter(Context context, int resource, int textViewResourceId, String[] data) 
		{
			super(context, resource, textViewResourceId, data);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.data = data;
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(int pos, View convertView, ViewGroup parent)
		{
			View v = convertView;
			if (v == null)
			{
				v = inflater.inflate(R.layout.row, parent, false);
			}

			TextView item = (TextView) v.findViewById(R.id.item);
			Button plus = (Button) v.findViewById(R.id.plus);
			EditText amount = (EditText) v.findViewById(R.id.num);
			Button minus = (Button) v.findViewById(R.id.minus);
			

			item.setText(data[pos]);
			
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
