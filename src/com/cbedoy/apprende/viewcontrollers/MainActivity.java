//--------------------------------------------
//
//	Apprende
//  Develop by Carlos Alfredo Cervantes Bedoy
//
//	Android Developer
//
//	Independent project:	carlos.bedoy@gmail.com
//
//	Aguascalientes | Mexico
//-------------------------------------------------------
package com.cbedoy.apprende.viewcontrollers;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cbedoy.apprende.bussiness.MasterController;
import com.cbedoy.apprende.bussiness.MasterViewController;
import com.cbedoy.apprende.services.ServiceAllUsuarios;
import com.uandroides.aprende.R;

public class MainActivity extends Activity {

	private ActionBar action;
	private EditText usuario_txt, password_txt;
	private Button botonLogin;
	private Intent intent;
	public SharedPreferences shared;
	public static MainActivity mthis;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		overridePendingTransition(R.anim.push_up, R.anim.push_up);


		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        
	}



}
