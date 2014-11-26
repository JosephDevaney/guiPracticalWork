package com.GUI.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{
	EditText name, age, availSpend, email;
	TextView genderLabel;
	AutoCompleteTextView jobs;
	Spinner jobList;
	RadioGroup gender;
	RadioButton male, female;
	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name = (EditText) findViewById(R.id.name);
		genderLabel = (TextView) findViewById(R.id.genderLabel);
		gender = (RadioGroup) findViewById(R.id.gender);
		male = (RadioButton) findViewById(R.id.male);
		female = (RadioButton) findViewById(R.id.female);
		age = (EditText) findViewById(R.id.age);
		jobs = (AutoCompleteTextView) findViewById(R.id.jobEdit);
		jobList = (Spinner) findViewById(R.id.jobSpinner);
		availSpend = (EditText) findViewById(R.id.moneyToSpend);
		email = (EditText) findViewById(R.id.email);
		submit = (Button) findViewById(R.id.submitBut);
		
		ArrayAdapter<CharSequence> jobListAdapter = ArrayAdapter.createFromResource(this, R.array.jobList, android.R.layout.simple_spinner_item);
		jobListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		jobList.setAdapter(jobListAdapter);
		jobList.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) 
			{
				String job = (String) parent.getItemAtPosition(pos);
				jobs.setText(job);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		String[] jobList = getResources().getStringArray(R.array.jobList);
		ArrayAdapter<String> jobsComplete = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, jobList);
		jobs.setAdapter(jobsComplete);
		
		
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) 
	{
		// TODO Auto-generated method stub
		if (checkInput())
		{
			User u;
			
			String name1 = name.getText().toString();
			String gender;
			if (male.isChecked())
			{
				gender = male.getText().toString();
			}
			else
			{
				gender = female.getText().toString();
			}
			int age = Integer.parseInt(this.age.getText().toString());
			String job = jobs.getText().toString();
			double money = Double.parseDouble(availSpend.getText().toString());
			String email = this.email.getText().toString();
			
			u = new User(name1, gender, age, job, money, email);
		
		
			Intent chooseItems = new Intent(MainActivity.this, ChooseItemActivity.class);
			chooseItems.putExtra("user", u);
			startActivity(chooseItems);
		}
		else
		{
			Toast.makeText(getApplicationContext(), getString(R.string.inputError), Toast.LENGTH_LONG).show();
		}
		
	}
	
	public Boolean checkInput()
	{
		if (name.getText().toString() == "")
		{
			return false;
		}
		if (!(male.isChecked() || female.isChecked()))
		{
			return false;
		}
		if (age.getText().toString() == "")
		{
			return false;
		}
		if (jobs.getText().toString() == "")
		{
			return false;
		}
		if (availSpend.getText().toString() == "")
		{
			return false;
		}
		if (email.getText().toString() == "")
		{
			return false;
		}
		
		return true;
	}
		
}
