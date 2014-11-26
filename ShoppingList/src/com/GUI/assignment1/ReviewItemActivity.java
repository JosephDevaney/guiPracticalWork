package com.GUI.assignment1;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ReviewItemActivity extends Activity
{
	User user;
	ArrayList<Item> chosenItems;
	double totMoney = 0;
	
	String emailBody = "";	//String to build the body of the email in
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_items);
		
		addRowToEmailStr(getString(R.string.nameHead), getString(R.string.qtyHead), getString(R.string.priceHead), 
				getString(R.string.vatHead), getString(R.string.totalHead));	//add Header row to email body
		
		user = getIntent().getExtras().getParcelable("user");	//get user
		chosenItems = getIntent().getParcelableArrayListExtra("chosenItems");	//get List of chosenItems
		
		TableLayout table = (TableLayout) findViewById(R.id.review_items);
		Button email = (Button) findViewById(R.id.emailBut);
		email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)	//When Send Email button is clicked
			{
				// TODO Auto-generated method stub
				
				/**
				 * Reference
				 * Email Intent code created from examples at:
				 * http://stackoverflow.com/questions/8701634/send-email-intent
				 * http://www.tutorialspoint.com/android/android_sending_email.htm
				 */
				Intent emailIntent = new Intent(Intent.ACTION_SEND);	//create email intent
				emailIntent.setType("message/rfc822");	//set the type of content. This one only suggests email apps
				emailIntent.putExtra(Intent.EXTRA_EMAIL, user.getEmail());	//Set the from email
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailSubject));	//Set the subject
				emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);	//Set the body of the email
				
				startActivity(Intent.createChooser(emailIntent, "Email the List"));	//Start the intent, bring up a list of email apps to continue with
				finish();	//close the current intent
				//End reference of email
			}
		});
		
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
			
			totMoney += totalPrice;
			
			/**
			 * Used examples from android api documentation and StackOverflow when researching how to create
			 * Views dynamically.
			 * https://developer.android.com/guide/topics/ui/layout/grid.html
			 * http://androidexample.com/Table_Layout_-_Android_Example/index.php?view=article_discription&aid=74&aaid=98
			 * http://stackoverflow.com/questions/1851633/how-to-add-button-dynamically-in-android
			 * 
			 */
			
			TableRow row = new TableRow(this);	//Statically display Data by dynamically creating View elements or TableRow, TextViews
			
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
			
			addRowToEmailStr(name.getText().toString(), qty.getText().toString(), preVat.getText().toString(), 
					vat.getText().toString(), totalCost.getText().toString());
			
			table.addView(row);
		}
		
		TableRow row = new TableRow(this);
		
		TextView totSpent = new TextView(this);
		totSpent.setText(getString(R.string.totalSpent) + ": " + df.format(totMoney));
		
		row.addView(totSpent);
		table.addView(row);
		
		emailBody += "\n\n" + totSpent.getText().toString() + "\n";
		
		
	}
	
	
	/**
	 * addRowToEmailStr() formats the body string with rows of data that is passed to it.
	 * @param n name
	 * @param q quantity
	 * @param p price pre vat
	 * @param v VAT
	 * @param t Total price
	 */
	private void addRowToEmailStr(String n, String q, String p, String v, String t)	
	{
		emailBody += n + "\t\t" + q + "\t\t" + p + "\t\t" + v + 
				"\t\t" + t + "\n";
	}
	
	
	

}
