package com.cbedoy.apprende;

import java.util.HashMap;

import org.json.JSONObject;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.interfaces.IAsyncServiceDelegate;
import com.cbedoy.apprende.keysets.ServiceKeySet;
import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class LoginView extends Activity implements IAsyncServiceDelegate{

	private MasterController masterController;
	private EditText username;
	private EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_login_view);

		((TextView) findViewById(R.id.version_name)).setTypeface(AppInstanceProvider.lightFont);
		((Button) 	findViewById(R.id.login_action)).setTypeface(AppInstanceProvider.lightFont);
		((EditText) findViewById(R.id.username_field)).setTypeface(AppInstanceProvider.lightFont);
		((EditText) findViewById(R.id.password_field)).setTypeface(AppInstanceProvider.lightFont);
		this.username = (EditText)findViewById(R.id.username_field);
		this.password = (EditText)findViewById(R.id.password_field);
		
		this.username.setText("cbedoy");
		this.password.setText("nomeacuerdo");
		
		View.OnClickListener loginAction = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				requestLogin();
			}
		};
		
		findViewById(R.id.login_action).setOnClickListener(loginAction);
	}
	
	private void requestLogin(){
		HashMap<String, Object> information = new HashMap<String, Object>();
		information.put("username", this.username.getText().toString());
		information.put("password", this.password.getText().toString());
		AppInstanceProvider.getInstance().setAnsycServiceDelegate(this);
		masterController = AppInstanceProvider.getInstance().instanceServiceWithEnum(
																				this, 
																				information, 
																				ServiceKeySet.GET_USER_INFO);
		masterController.getAsyncServiceController().execute();
		
	}

	@Override
	public void reloadData(JSONObject jsonObject) {
		Intent intent = new Intent(this, ProfileView.class);
		startActivity(intent);
		
	}
}
