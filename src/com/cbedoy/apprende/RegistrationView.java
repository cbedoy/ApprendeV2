package com.cbedoy.apprende;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.viewdelegates.IUniversityViewDelegate;
import com.cbedoy.apprende.keysets.CourseKeySet;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.keysets.UserKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationView extends Activity implements IUniversityViewDelegate{

	private EditText 				username;
	private EditText 				password;
	private EditText 				confirmPassword;
	private EditText 				birthday;
	private EditText 				firstName;
	private EditText 				lastName;
	private Button 					finishRegistrationAction;
	private Spinner 				universityList;
	private List<String> 			informationUniversity;
	private MasterController 		masterController;
	private ArrayAdapter<String> 	universityAdapter;
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
		this.informationUniversity      = new ArrayList<String>();
		this.universityAdapter 			= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.informationUniversity);
		this.username.setTypeface(AppInstanceProvider.lightFont);
		this.password.setTypeface(AppInstanceProvider.lightFont);
		this.confirmPassword.setTypeface(AppInstanceProvider.lightFont);
		this.birthday.setTypeface(AppInstanceProvider.lightFont);
		this.firstName.setTypeface(AppInstanceProvider.lightFont);
		this.lastName.setTypeface(AppInstanceProvider.lightFont);
		this.finishRegistrationAction.setTypeface(AppInstanceProvider.lightFont);
		this.universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.masterController = AppInstanceProvider.getInstance().instanceServiceUniversity(this, ServiceKeySet.GET_UNIVERSITY);
		this.masterController.getAnsycTask().execute();
	}

	@Override
	public void reloadDataWithUniversity(JSONArray response) {
		this.informationUniversity.clear();
		try {
			if(response!=null){
				for(int index = 0; index < response.length(); index++){
					JSONObject data 					= response.getJSONObject(index);
					JSONObject fields					= data.getJSONObject("fields");
					this.informationUniversity.add(fields.get("name").toString());
				
				}
				this.universityList.setAdapter(this.universityAdapter);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
