package com.GUI.assignment1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseItemActivity extends ListActivity
{
	User user;
    ArrayList<Item> items;
    ArrayList<Item> chosenItems;
    
    /**
     * ViewHolder pattern: references
     * http://developer.android.com/training/improving-layouts/smooth-scrolling.html
     * http://java.dzone.com/articles/android-listview-optimizations
     *
     */
    static class ViewHolder
    {
    	TextView item;
    	TextView price;
    	Button plus;
    	EditText amount;
    	Button minus;
    }
    //end Reference
    
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
			user = getIntent().getExtras().getParcelable("user");	//get the user object from the previous intent with getParcelable. A new user objetc is created with the same information
			this.data = data;
			Random r = new Random();	//To save time, prices will be random, in a real app the prices would be stored in resources
			
			items = new ArrayList<>();
			for (int i = 0; i < data.length; i++)
			{
				Item it = new Item();	//Create a list of Item objects from the array of items in resources
				it.setName(data[i]);
				it.setPrice(r.nextDouble() * 20);
				it.setAmount(0);
				it.setDescription("This is an example description for " + it.getName() + ". In the real application "
						+ "this would be gotten from a resource.");
				items.add(it);
			}
			
			chosenItems = new ArrayList<>();
			
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(final int pos, View convertView, ViewGroup parent)
		{
			View v = convertView;
			
			/**
		     * ViewHolder pattern: references
		     * http://developer.android.com/training/improving-layouts/smooth-scrolling.html
		     * http://java.dzone.com/articles/android-listview-optimizations
		     *
		     */
			ViewHolder holder = null;	//ViewHolder pattern improves the efficiency of the list
			if (v == null)	//row needs to be inflated
			{
				v = inflater.inflate(R.layout.row, parent, false);
				holder = new ViewHolder();
				
				holder.item = (TextView) v.findViewById(R.id.item);
				holder.price = (TextView) v.findViewById(R.id.price);
				holder.plus = (Button) v.findViewById(R.id.plus);
				holder.amount = (EditText) v.findViewById(R.id.num);
				holder.minus = (Button) v.findViewById(R.id.minus);
								
				v.setTag(holder);
			}
			else	//row has already been inflated, get the holder object
			{
				holder = (ViewHolder) v.getTag();
			}
			//End ViewHolder References
			
			/**
			 * Decimal Format examples used to format price output.
			 * Examples taken from StackOverFlow and Android Developer API documentation
			 * http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
			 * http://developer.android.com/reference/java/text/DecimalFormat.html
			 */
			DecimalFormat df = new DecimalFormat("€##0.00");

			holder.item.setText(items.get(pos).getName());
			holder.item.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v)	//When the Name field is clicked
				{
					// TODO Auto-generated method stub
					
					/**
					 * Alert Dialog code created from examples on android api documentation at:
					 * http://developer.android.com/guide/topics/ui/dialogs.html
					 * http://developer.android.com/reference/android/app/Dialog.html
					 */
					
					AlertDialog.Builder builder = new AlertDialog.Builder(getContext());	//Create and setup the builder
					builder	.setTitle(R.string.description)
							.setMessage(items.get(pos).getDescription())
							.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									// TODO Auto-generated method stub
									dialog.cancel();	//close dialog
								}
							});
					AlertDialog dialog = builder.create();	//Create and show the dialog
					dialog.show();
				}
			});
			//End Alert Dialog reference
			
			holder.price.setText(df.format(items.get(pos).getPrice()));
			holder.plus.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {	
					// TODO Auto-generated method stub
					
					Item i = items.get(pos);
					int amt = i.getAmount();
					if (amt == 0) 	// If amt is going from 0 to 1, add it to the chosenItems list
					{
						chosenItems.add(i);
					}
					amt++;
					i.setAmount(amt);
					notifyDataSetChanged();	//Tell the activity that the data has changed and that it needs to update
			
				}
			});

			holder.amount.setText(Integer.toString(items.get(pos).getAmount()));
			
			/**
			 * Below is commented out code that I had tried to use to implement functions 
			 * to allow the user to click on the edittext and change the quantity of that particular item
			 * However, I was unable to get this to function correctly so I have partly omitted it.
			 */
			/*
			holder.amount.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count)
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
						num = 0;
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
					
				}
			});*/
			
			holder.minus.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Item i = items.get(pos);
					int amt = i.getAmount();
					
					if (amt > 0)
					{
						if (amt == 1) 	//if amt is going from 1 to 0, then remove it from the chosenItems list
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
			public void onClick(View v)	// On submit clicked
			{
				// TODO Auto-generated method stub
				double moneyDiff = budgetDifference();
				if (moneyDiff >= 0)	// If user is under budget
				{
					Intent review = new Intent(ChooseItemActivity.this, ReviewItemActivity.class);
					review.putParcelableArrayListExtra("chosenItems", chosenItems);
					review.putExtra("user", user);
					startActivity(review);
				}
				else	//Tell user how much they have overspent by
				{
					DecimalFormat df = new DecimalFormat("€###0.00");
					String message = getString(R.string.spentTooMuch) + " " + df.format(Math.abs(moneyDiff));
					Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
				}
				
				
			}
		});
		
		
		
		
	}
	
	//Get the difference between the budget and the total spent
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
