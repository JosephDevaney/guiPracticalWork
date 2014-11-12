package com.GUI.assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText name, age, availSpend;
	TextView genderLabel;
	AutoCompleteTextView jobs;
	Spinner jobList;
	RadioGroup gender;
	RadioButton male, female;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
}
