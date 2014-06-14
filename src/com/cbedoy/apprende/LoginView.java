package com.cbedoy.apprende;

import com.cbedoy.apprende.services.AppInstanceProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class LoginView extends Activity {

		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view);

		((TextView) findViewById(R.id.version_name)).setTypeface(AppInstanceProvider.lightFont);
		((Button) 	findViewById(R.id.login_action)).setTypeface(AppInstanceProvider.lightFont);
		((EditText) findViewById(R.id.username_field)).setTypeface(AppInstanceProvider.lightFont);
		((EditText) findViewById(R.id.password_field)).setTypeface(AppInstanceProvider.lightFont);
		
		View.OnClickListener loginAction = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		};
		
		findViewById(R.id.login_action).setOnClickListener(loginAction);
		
	}
}
