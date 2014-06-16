package com.cbedoy.apprende;

import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationView extends Activity {

	private EditText 	username;
	private EditText 	password;
	private EditText 	confirmPassword;
	private EditText 	birthday;
	private EditText 	firstName;
	private EditText 	lastName;
	private Button 		finishRegistrationAction;
	private Spinner 	universityList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_view);
		
		this.username 					= (EditText)findViewById(R.id.registration_email);
		this.password 					= (EditText)findViewById(R.id.registration_password);
		this.confirmPassword 			= (EditText)findViewById(R.id.registration_confirm_password);
		this.birthday 					= (EditText)findViewById(R.id.registration_birthday);
		this.firstName 					= (EditText)findViewById(R.id.registration_first_name);
		this.lastName 					= (EditText)findViewById(R.id.registration_last_name);
		this.finishRegistrationAction 	= (Button)findViewById(R.id.registration_finish);
		this.universityList 			= (Spinner)findViewById(R.id.registration_university);
		
		this.username.setTypeface(AppInstanceProvider.lightFont);
		this.password.setTypeface(AppInstanceProvider.lightFont);
		this.confirmPassword.setTypeface(AppInstanceProvider.lightFont);
		this.birthday.setTypeface(AppInstanceProvider.lightFont);
		this.firstName.setTypeface(AppInstanceProvider.lightFont);
		this.lastName.setTypeface(AppInstanceProvider.lightFont);
		this.finishRegistrationAction.setTypeface(AppInstanceProvider.lightFont);
		
	}
}
