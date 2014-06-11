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

import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.uandroides.aprende.R;

public class SplashActivity extends Activity {

	private long splashDelay = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_view);
		
		ActionBar a = getActionBar();
		a.hide();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		TimerTask task = new TimerTask() {
		      @Override
		      public void run() {
		        Intent mainIntent = new Intent().setClass(SplashActivity.this, MainActivity.class);
		        startActivity(mainIntent);
		        finish();
		      }
		    };

		    Timer timer = new Timer();
		    timer.schedule(task, splashDelay);//Pasado los 6 segundos dispara la tarea
		  }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
