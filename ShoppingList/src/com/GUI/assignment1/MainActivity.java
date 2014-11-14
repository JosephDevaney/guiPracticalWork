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
		age = (EditText) findViewById(R.id.name);
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
		Intent chooseItems = new Intent(MainActivity.this, ChooseItemActivity.class);/*
		chooseItems.putExtra("name", name.getText());
		if (male.isChecked())
		{
			chooseItems.putExtra("gender", male.getText());
		}
		else
		{
			chooseItems.putExtra("gender", female.getText());
		}
		chooseItems.putExtra("age", age.getText());
		chooseItems.putExtra("job", jobs.getText());
		chooseItems.putExtra("money", availSpend.getText());
		chooseItems.putExtra("email", email.getText());*/
		
		startActivity(chooseItems);
	}

	
}
